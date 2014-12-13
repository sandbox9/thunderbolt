package sandbox9.thunderbolt.site.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sandbox9.thunderbolt.entity.catalog.Catalog;
import sandbox9.thunderbolt.entity.catalog.client.CatalogApi;

/**
 * Created by chanwook on 2014. 12. 12..
 */
@Controller
public class CatalogViewController {

    @Autowired
    private CatalogApi catalogApi;

    @RequestMapping(value = "/catalog/{catalogId}")
    public String viewCatalog(@PathVariable String catalogId, ModelMap model) {
        Catalog catalog = catalogApi.getOne(catalogId);
        model.put("catalog", catalog);
        return "/catalog";
    }
}
