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
import sandbox9.thunderbolt.message.product.ProductSkuPriceEvent;

import java.util.List;
import java.util.Map;

/**
 * Created by chanwook on 2014. 12. 11..
 */
public class ProductPriceEventActivity implements EventProcessActivity {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductEventRepository er;

    @Override
    public void handleActivity(Object eventSeed) {
        List<Product> productList = (List<Product>) eventSeed;

        // TODO 별도 컴포넌트로 설계해 빼내기
        Map<Integer, List<ProductSkuEvent>> eventMap = er.findEvent(productList, ProductEventKey.PRICE);

        for (Product p : productList) {
            if (!eventMap.containsKey(p.getProductId())) {
                logger.debug(p.getProductId() + " 상품에 해당하는 이벤트가 존재하지 않습니다.");
                continue;
            }

            // 가격 이벤트 처리
            List<ProductSkuEvent> eventList = eventMap.get(p.getProductId());

            for (ProductSkuEvent event : eventList) {
                Sku sku = p.getSku(event.getSkuId());
                if (sku == null) {
                    logger.warn(p.getProductId() + " 상품에 " + event.getSkuId() + "에 해당하는 Sku 데이터가 존재하지 않습니다.");
                    continue;
                }
                ProductSkuPriceEvent priceEvent = (ProductSkuPriceEvent) event;
                long beforePrice = sku.getSalePrice();
                if (EventCalculationType.PLUS.equals(priceEvent.getCalculationType())) {
                    sku.setSalePrice(beforePrice + priceEvent.getChangeValue());
                } else if (EventCalculationType.MINUS.equals(priceEvent.getCalculationType())) {
                    sku.setSalePrice(beforePrice - priceEvent.getChangeValue());
                }
                logger.info("Sku 가격 변경 내역[" + p.getProductId() + " - " + sku.getSkuId() + "]" + beforePrice +
                        " => " + sku.getSalePrice() + "(" + priceEvent.getCalculationType() + ", " + priceEvent.getChangeValue() + ")");
            }
        }
    }
}
