package sandbox9.thunderbolt.event.product.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.entity.product.Sku;
import sandbox9.thunderbolt.event.product.message.EventCalculationType;
import sandbox9.thunderbolt.event.product.message.ProductEvent;

/**
 * Created by chanwook on 2014. 12. 16..
 */
public class ProductStockEventHandler implements ProductEventHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void process(Product product, ProductEvent event) {
        Sku sku = product.getSku(event.getSkuId());
        if (sku == null) {
            logger.warn(product.getProductId() + " 상품에 " + event.getSkuId() +
                    "에 해당하는 Sku 데이터가 존재하지 않습니다.");
            return;
        }
        long stockSnapshot = sku.getStock();
        if (EventCalculationType.PLUS.equals(event.getCalculationType())) {
            sku.setStock(stockSnapshot + (Integer) event.getValue());
        } else if (EventCalculationType.MINUS.equals(event.getCalculationType())) {
            long stock = stockSnapshot - (Integer) event.getValue();
            if (stock < 0) {
                logger.warn(product.getProductId() + " 상품 " + sku.getSkuId() + " Sku의 재고가 - 값으로 변경되어 0으로 처리합니다.");
                sku.setStock(0);
            } else {
                sku.setStock(stock);
            }
        }
        logger.info("Sku 재고 변경 내역[" + product.getProductId() + " - " + sku.getSkuId() + "]" + stockSnapshot +
                " => " + sku.getStock() + "(" + event.getCalculationType() + ", " + event.getValue() + ")");
    }
}
