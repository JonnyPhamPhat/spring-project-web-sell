package springwebsell.websell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springwebsell.websell.model.Laptop;
import springwebsell.websell.repository.LaptopRepository;
import springwebsell.websell.service.ILaptopService;

import java.util.List;

@Service
public class LaptopService implements ILaptopService {

    @Autowired
    private LaptopRepository laptopRepository;

    @Override
    public List<Laptop> findAll() {
        return laptopRepository.findAll();
    }

    @Override
    public Laptop save(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    @Override
    public Laptop findById(Long id) {
        return laptopRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        Laptop laptop = laptopRepository.getById(id);
        laptop.set_deleted(true);
        laptop.set_activated(false);
        laptopRepository.save(laptop);
    }

    @Override
    public void enableById(Long id) {
        Laptop laptop = laptopRepository.getById(id);
        laptop.set_activated(true);
        laptop.set_deleted(false);
        laptopRepository.save(laptop);
    }

    @Override
    public Page<Laptop> pageLaptops(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        Page<Laptop> pageLatop = laptopRepository.pageLaptop(pageable);
        return pageLatop;
    }
}
