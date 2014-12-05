package sandbox9.thunderbolt.product.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chanwook on 2014. 12. 5..
 */
@RestController
public class HelloWorldRestController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String greeting() {
        return "hello!";
    }
}
