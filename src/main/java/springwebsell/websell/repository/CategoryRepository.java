package springwebsell.websell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springwebsell.websell.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
