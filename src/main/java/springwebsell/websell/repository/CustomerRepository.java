package springwebsell.websell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springwebsell.websell.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
}
