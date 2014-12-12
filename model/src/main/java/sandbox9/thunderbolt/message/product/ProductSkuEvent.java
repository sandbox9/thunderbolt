package sandbox9.thunderbolt.message.product;

import java.io.Serializable;

/**
 * Created by chanwook on 2014. 12. 12..
 */
public interface ProductSkuEvent extends Serializable {

    String getId();

    int getProductId();

    int getSkuId();
}
