package sandbox9.thunderbolt.event.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sandbox9.thunderbolt.message.product.ProductSkuPriceEvent;
import sandbox9.thunderbolt.event.repository.ProductEventRepository;

/**
 * Created by chanwook on 2014. 12. 9..
 */
public class ProductPricingEventHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ProductEventRepository productEventRepository;

    public ProductPricingEventHandler(ProductEventRepository productEventRepository) {
        this.productEventRepository = productEventRepository;
    }

    public void handleMessage(ProductSkuPriceEvent event) {
        logger.info("[상품 가격 이벤트 수신] " + event);

        /**
         * 상품 가격 정보가 변경된 걸 받아서 두 가지 방법으로 해볼 수 있을 듯.
         * 1. Event-driven 개념 활용: event 테이블로 insert
         * 2. Snapshot 개념만 활용: 상품과 카탈로그 snapshot으로 바로 쏴줌
         */
        try {
            productEventRepository.save(event);
        } catch (Exception e) {
            logger.error("상품 이벤트 정보 저장 중 예외가 발생했습니다![이벤트정보: " + event +
                    "(TODO: 예외 메시지 별도 처리 매카니즘 적용)", e);
            return;
        }

        logger.info("[상품 가격 이벤트 처리 완료] " + event);

    }
}
