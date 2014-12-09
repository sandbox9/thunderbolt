package sandbox9.thunderbolt.admin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chanwook on 2014. 12. 8..
 */
public class ProductViewModelMap implements Serializable {

    private List<ProductViewModel> productList = new ArrayList<ProductViewModel>();

    public List<ProductViewModel> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductViewModel> productList) {
        this.productList = productList;
    }

    public void addProduct(ProductViewModel model) {
        this.productList.add(model);
    }
}
