package springwebsell.websell.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import springwebsell.websell.model.Laptop;
import springwebsell.websell.model.SmartPhone;

import java.util.List;

public interface IPhoneService {
    List<SmartPhone> findAll();
    SmartPhone save(SmartPhone smartPhone);
    SmartPhone findById(Long id);
    void delete(Long id);
    void enable(Long id);
    Page<SmartPhone> pagePhone(int pageNo);
    Page<SmartPhone> pageAllPhone(int pageNo);
    List<SmartPhone> getRelatedProducts(Long categoryId);
}
