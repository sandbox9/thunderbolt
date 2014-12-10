package sandbox9.thunderbolt.product.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sandbox9.thunderbolt.message.product.ProductSkuPriceEvent;

/**
 * Created by chanwook on 2014. 12. 9..
 */
public class ProductEventForLogHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void handleMessage(ProductSkuPriceEvent event) {
        logger.info("[상품 수정 이벤트 수신] " + event.toString());
    }
}
