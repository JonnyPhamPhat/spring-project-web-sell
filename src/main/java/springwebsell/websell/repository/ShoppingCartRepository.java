package springwebsell.websell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springwebsell.websell.model.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
