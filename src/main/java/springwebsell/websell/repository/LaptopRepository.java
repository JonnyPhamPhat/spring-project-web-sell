package springwebsell.websell.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springwebsell.websell.model.Laptop;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    @Query("SELECT p FROM Laptop p")
    Page<Laptop> pageLaptop(Pageable pageable);
}
