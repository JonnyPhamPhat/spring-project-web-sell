package springwebsell.websell.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springwebsell.websell.model.SmartPhone;

import java.util.List;

public interface PhoneRepository extends JpaRepository<SmartPhone, Long> {
    @Query("SELECT p FROM SmartPhone p")
    Page<SmartPhone> pagePhone(Pageable pageable);

    @Query("SELECT p FROM SmartPhone p")
    Page<SmartPhone> pageAllPhone(Pageable pageable);

    @Query("SELECT p FROM SmartPhone p INNER JOIN Category c ON c.id = p.category.id WHERE p.category.id = ?1")
    List<SmartPhone> getRelateProducts(Long categoryId);
}
