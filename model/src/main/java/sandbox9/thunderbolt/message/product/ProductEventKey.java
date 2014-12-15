package sandbox9.thunderbolt.message.product;

/**
 * Created by chanwook on 2014. 12. 11..
 */
public enum ProductEventKey {
    PRICE, STOCK, ETC;

    public static Class<? extends ProductSkuEvent> getEventClass(ProductEventKey eventKey) {
        Class<? extends ProductSkuEvent> eventClass = null;
        if (PRICE.equals(eventKey)) {
            eventClass = ProductSkuPriceEvent.class;
        } else if (STOCK.equals(eventKey)) {
            eventClass = ProductSkuStockEvent.class;
        } else {
            throw new IllegalArgumentException(eventKey + "에 해당하는 이벤트 클래스가 존재하지 않습니다.");
        }
        return eventClass;
    }
}
