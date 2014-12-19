package sandbox9.thunderbolt.api.order.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import sandbox9.thunderbolt.api.customer.model.Customer;

/**
 * Created by chanwook on 2014. 12. 18..
 */
public interface CustomerJpaRepository extends PagingAndSortingRepository<Customer, String> {
}
