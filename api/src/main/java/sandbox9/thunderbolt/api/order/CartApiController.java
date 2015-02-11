package sandbox9.thunderbolt.api.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sandbox9.thunderbolt.api.order.model.Order;
import sandbox9.thunderbolt.api.order.service.OrderService;

/**
 * Created by chanwook on 2014. 12. 22..
 */
@RestController
public class CartApiController {

    @Autowired
    private OrderService orderService;

    // cart 조회
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ResponseEntity getCart(@RequestParam(value = "customerId") String customerId) {
        Order cart = orderService.getCart(customerId);
        if (cart == null) {
            // cart 생성하라고 응답보내기
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.LOCATION, "/cart/create");
            return new ResponseEntity(headers, HttpStatus.MOVED_PERMANENTLY);
        }
        return new ResponseEntity(cart, HttpStatus.OK);
    }
}
