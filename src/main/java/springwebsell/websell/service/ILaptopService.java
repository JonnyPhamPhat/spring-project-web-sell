package springwebsell.websell.service;

import org.springframework.data.domain.Page;
import springwebsell.websell.model.Laptop;

import java.util.List;

public interface ILaptopService {
    List<Laptop> findAll();
    Laptop save(Laptop laptop);
    Laptop findById(Long id);
    void delete(Long id);
    void enableById(Long id);
    Page<Laptop> pageLaptops(int pageNo);
    Page<Laptop> pageAllLaptops(int pageNo);
    List<Laptop> getRelatedProducts(Long categoryId);
}
