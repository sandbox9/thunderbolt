package sandbox9.thunderbolt.admin.model;

import sandbox9.thunderbolt.product.model.Product;
import sandbox9.thunderbolt.product.model.Sku;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chanwook on 2014. 12. 8..
 */
public class ProductViewModel extends Product {

    private boolean update;

    private List<Sku> updateSkuList = new ArrayList<Sku>();

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public List<Sku> getUpdateSkuList() {
        return updateSkuList;
    }

    public void setUpdateSkuList(List<Sku> updateSkuList) {
        this.updateSkuList = updateSkuList;
    }
}
