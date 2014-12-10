package sandbox9.thunderbolt.product.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sandbox9.thunderbolt.product.repository.ProductEventRepository;
import sandbox9.thunderbolt.entity.product.repository.ProductRepository;
import sandbox9.thunderbolt.message.product.ProductSkuPriceEvent;
import sandbox9.thunderbolt.message.product.SkuPricingEventType;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.entity.product.Sku;

import java.util.List;
import java.util.Map;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductRepository r;

    @Autowired
    private ProductEventRepository er;

    @Override
    public List<Product> find(int pageNumber, int pageSize) {
        Page<Product> productPage = r.findAll(new PageRequest(pageNumber, pageSize));
        List<Product> productList = productPage.getContent();

        // 별도 컴포넌트로 설계해 빼내기
        Map<Integer, List<ProductSkuPriceEvent>> eventMap = er.findEvent(productList);

        for (Product p : productList) {
            if (!eventMap.containsKey(p.getProductId())) {
                logger.debug(p.getProductId() + " 상품에 해당하는 이벤트가 존재하지 않습니다.");
                continue;
            }

            // 가격 이벤트 처리
            List<ProductSkuPriceEvent> eventList = eventMap.get(p.getProductId());

            for (ProductSkuPriceEvent event : eventList) {
                Sku sku = p.getSku(event.getSkuId());
                if (sku == null) {
                    logger.warn(p.getProductId() + " 상품에 " + event.getSkuId() + "에 해당하는 Sku 데이터가 존재하지 않습니다.");
                    continue;
                }
                long beforePrice = sku.getSalePrice();
                if (SkuPricingEventType.PLUS.equals(event.getEventType())) {
                    sku.setSalePrice(beforePrice + event.getChangeValue());
                } else if (SkuPricingEventType.MINUS.equals(event.getEventType())) {
                    sku.setSalePrice(beforePrice - event.getChangeValue());
                }
                logger.info("Sku 가격 변경 내역[" + p.getProductId() + " - " + sku.getSkuId() + "]" + beforePrice +
                        " => " + sku.getSalePrice() + "(" + event.getEventType() + ", " + event.getChangeValue() + ")");
            }
        }

        return productList;
    }
}
