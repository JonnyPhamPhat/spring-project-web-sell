package springwebsell.websell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springwebsell.websell.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
