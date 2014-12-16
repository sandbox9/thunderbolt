package sandbox9.thunderbolt.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandbox9.thunderbolt.admin.model.ProductViewModel;
import sandbox9.thunderbolt.admin.model.ProductViewModelMap;
import sandbox9.thunderbolt.entity.product.Sku;
import sandbox9.thunderbolt.event.product.message.ProductEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static sandbox9.thunderbolt.event.product.message.EventCalculationType.MINUS;
import static sandbox9.thunderbolt.event.product.message.EventCalculationType.PLUS;
import static sandbox9.thunderbolt.event.product.message.ProductEvent.ProductEventType.PRICE;
import static sandbox9.thunderbolt.event.product.message.ProductEvent.ProductEventType.STOCK;

/**
 * Created by chanwook on 2014. 12. 8..
 */
@Service
public class ProductEventHandleServiceImpl implements ProductEventHandleService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void handle(ProductViewModelMap model) {
        List<ProductEvent> eventList = new ArrayList<ProductEvent>();

        // 우선 상품 화면 모델에 있는 데이터를 가지고 바로 사용하자. 나중에 변경 필요
        for (ProductViewModel m : model.getProductList()) {
            if (m.isUpdate()) {
                logger.debug("상품 정보 갱신 플래그가 오픈되어 이벤트 생성을 시작합니다.");

                for (int i = 0; i < m.getSkuList().size(); i++) {
                    Sku update = m.getUpdateSkuList().get(i);
                    Sku original = m.getSku(update.getSkuId());

                    //TODO 추상화
                    handleSalePriceEvent(eventList, update, original, m.getProductId());
                    handleStockEvent(eventList, update, original, m.getProductId());
                }
                logger.debug("상품 정보 이벤트 생성 완료: " + eventList.size() + "개 이벤트 생성");
            } else {
                logger.debug("상품 정보 변경이 발생하지 않았습니다.");
            }
        }

        //TODO 추상화
        produceProductChangeEvent(eventList);
    }

    private void handleStockEvent(List<ProductEvent> eventList, Sku update, Sku original, int productId) {
        if (original.getStock() > update.getStock()) {
            // 재고가 줄었을 때...
            ProductEvent event = new ProductEvent(productId, original.getSkuId(),
                    original.getStock() - update.getStock(), STOCK, MINUS, new Date());
            eventList.add(event);
        } else if (original.getStock() < update.getStock()) {
            ProductEvent event = new ProductEvent(productId, original.getSkuId(),
                    update.getSalePrice() - original.getSalePrice(), STOCK, PLUS, new Date());
            eventList.add(event);
        } else {
            logger.debug("상품 정보가 변경 됐지만 재고는 변하지 않았습니다.");
        }
    }

    private void handleSalePriceEvent(List<ProductEvent> eventList, Sku update, Sku original, int productId) {
        if (original.getSalePrice() > update.getSalePrice()) {
            // 판매가를 내렸을 때...
            ProductEvent event = new ProductEvent(productId, original.getSkuId(),
                    original.getSalePrice() - update.getSalePrice(), PRICE, MINUS, new Date());
            eventList.add(event);
        } else if (original.getSalePrice() < update.getSalePrice()) {
            // 판매가를 올렸을 때...
            ProductEvent event = new ProductEvent(productId, original.getSkuId(),
                    update.getSalePrice() - original.getSalePrice(), PRICE, PLUS, new Date());
            eventList.add(event);
        } else {
            logger.debug("상품 정보가 변경 됐지만 가격은 변하지 않았습니다.");
        }
    }

    private void produceProductChangeEvent(List<ProductEvent> eventList) {
        for (ProductEvent e : eventList) {
            logger.info("[상품 이벤트 전송] " + e);
            rabbitTemplate.convertAndSend(e);
        }
    }
}
