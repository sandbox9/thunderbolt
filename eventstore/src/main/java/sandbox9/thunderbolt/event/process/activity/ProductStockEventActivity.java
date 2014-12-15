package sandbox9.thunderbolt.event.process.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.entity.product.Sku;
import sandbox9.thunderbolt.event.repository.ProductEventRepository;
import sandbox9.thunderbolt.message.product.EventCalculationType;
import sandbox9.thunderbolt.message.product.ProductEventKey;
import sandbox9.thunderbolt.message.product.ProductSkuEvent;
import sandbox9.thunderbolt.message.product.ProductSkuStockEvent;

import java.util.List;
import java.util.Map;

/**
 * Created by chanwook on 2014. 12. 11..
 */
public class ProductStockEventActivity implements EventProcessActivity {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductEventRepository er;

    @Override
    public void handleActivity(Object eventSeed) {
        List<Product> productList = (List<Product>) eventSeed;

        // TODO 별도 컴포넌트로 설계해 빼내기. CatalogProduct와 통합
        Map<Integer, List<ProductSkuEvent>> eventMap = er.findEvent(productList, ProductEventKey.STOCK);

        for (Product p : productList) {
            if (!eventMap.containsKey(p.getProductId())) {
                logger.debug(p.getProductId() + " 상품에 해당하는 이벤트가 존재하지 않습니다.");
                continue;
            }

            // 재고 이벤트 처리
            List<ProductSkuEvent> eventList = eventMap.get(p.getProductId());

            for (ProductSkuEvent e : eventList) {
                Sku sku = p.getSku(e.getSkuId());
                if (sku == null) {
                    logger.warn(p.getProductId() + " 상품에 " + e.getSkuId() + "에 해당하는 Sku 데이터가 존재하지 않습니다.");
                    continue;
                }
                ProductSkuStockEvent stockEvent = (ProductSkuStockEvent) e;
                long stockSnapshot = sku.getStock();
                if (EventCalculationType.PLUS.equals(stockEvent.getCalculationType())) {
                    sku.setStock(stockSnapshot + stockEvent.getChangeValue());
                } else if (EventCalculationType.MINUS.equals(stockEvent.getCalculationType())) {
                    long stock = stockSnapshot - stockEvent.getChangeValue();
                    if (stock < 0) {
                        logger.warn(p.getProductId() + " 상품 " + sku.getSkuId() + " Sku의 재고가 - 값으로 변경되어 0으로 처리합니다.");
                        sku.setStock(0);
                    } else {
                        sku.setStock(stock);
                    }
                }
                logger.info("Sku 재고 변경 내역[" + p.getProductId() + " - " + sku.getSkuId() + "]" + stockSnapshot +
                        " => " + sku.getStock() + "(" + stockEvent.getCalculationType() + ", " + stockEvent.getChangeValue() + ")");
            }
        }
    }
}
