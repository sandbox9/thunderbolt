package sandbox9.thunderbolt.event.product.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sandbox9.thunderbolt.event.product.message.ProductEvent;
import sandbox9.thunderbolt.event.product.repository.ProductEventRepository;

/**
 * Created by chanwook on 2014. 12. 16..
 */
public class ProductEventMessageListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ProductEventRepository productEventRepository;

    public ProductEventMessageListener(ProductEventRepository productEventRepository) {
        this.productEventRepository = productEventRepository;
    }

    public void handleMessage(ProductEvent event) {
        logger.info("[상품 이벤트 수신] " + event);

        try {
            productEventRepository.save(event);
        } catch (Exception e) {
            logger.error("상품 이벤트 정보 저장 중 예외가 발생했습니다![이벤트정보: " + event +
                    "(TODO: 예외 메시지 별도 처리 매카니즘 적용)", e);
            return;
        }

        logger.info("[상품 이벤트 처리 완료] " + event);
    }
}
