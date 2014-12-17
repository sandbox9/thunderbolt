package sandbox9.thunderbolt.eventstore.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.entity.product.repository.ProductRepository;
import sandbox9.thunderbolt.event.product.ProductEventStore;
import sandbox9.thunderbolt.event.product.message.ProductEvent;
import sandbox9.thunderbolt.event.product.repository.ProductEventRepository;

import java.util.List;

/**
 * 이벤트 정보를 Mongo snapshot DB로 동기화 진행
 * <p/>
 * <p/>
 * Created by chanwook on 2014. 12. 15..
 */
public class ProductUpdateJob {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductEventRepository eventRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductEventStore eventStore;

    public void updateProduct() {
        logger.info("상품 이벤트 정보 동기화 작업을 시작합니다.");
        ExecutionInfo info = new ExecutionInfo();

        updateProductByEvent(info);

        logger.info("상품 이벤트 정보 동기화 작업을 종료합니다." +
                " >> 시도 이벤트수: " + info.getTryCount() + "건, 성공 이벤트수: " + info.getSuccessCount() + "건, " +
                "실패 이벤트수: " + info.getFailCount() + "건");
    }

    private void updateProductByEvent(ExecutionInfo info) {
        //TODO 1. 현재 SKU 단위 처리 코드를 PRODUT 단위로 변경해야 함. 즉, 이벤트 테이블을 PRODUCT 단위로 돌아가도록 수정 필요
        //TODO 2. 중간에 실패하거나 예상치 못한 예외 발생으로 동기화 작업이 중단되었을 때에 대한 조치 필요: transaction 처리

        List<ProductEvent> eventList = eventRepository.findAll();

        for (ProductEvent event : eventList) {
            try {
                info.increaseTry();

                // 건건히 상품을 조회하고 반영. 그러니까 중간 처리 결과에 대한 조치가 필요하다
                Product product = productRepository.findOne(event.getProductId());
                eventStore.process(event, product);
                eventRepository.delete(event);
                productRepository.save(product);

                info.increaseSuccess();
            } catch (Exception ex) {
                logger.error("상품 정보 동기화 중 예상치 못한 예외가 발생했습니다. 해당 건은 처리하지 않습니다.[에러 이벤트 정보: " + event + "]", ex);
                info.increaseFail();
            }
        }
    }

    static class ExecutionInfo {
        int tryCount = 0;
        int failCount = 0;
        int successCount = 0;

        public void increaseTry() {
            ++tryCount;
        }

        public void increaseFail() {
            ++failCount;
        }

        public void increaseSuccess() {
            ++successCount;
        }

        public int getTryCount() {
            return tryCount;
        }

        public int getFailCount() {
            return failCount;
        }

        public int getSuccessCount() {
            return successCount;
        }
    }
}
