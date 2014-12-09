package sandbox9.thunderbolt.eventstore.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sandbox9.thunderbolt.product.message.ProductSkuPriceEvent;

/**
 * Created by chanwook on 2014. 12. 9..
 */
public class ProductEventForLogHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void recieveEvent(ProductSkuPriceEvent event) {
        logger.info("[Receive product event] " + event.toString());
    }
}
