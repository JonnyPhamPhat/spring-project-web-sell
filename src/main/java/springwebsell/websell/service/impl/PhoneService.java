package springwebsell.websell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springwebsell.websell.model.SmartPhone;
import springwebsell.websell.repository.PhoneRepository;
import springwebsell.websell.service.IPhoneService;

import java.util.List;

@Service
public class PhoneService implements IPhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private StoreService storeService;

    @Override
    public List<SmartPhone> findAll() {
        return phoneRepository.findAll();
    }

    @Override
    public SmartPhone save(SmartPhone smartPhone) {
        return phoneRepository.save(smartPhone);
    }

    @Override
    public SmartPhone findById(Long id) {
        return phoneRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        SmartPhone smartPhone = phoneRepository.getById(id);
        smartPhone.set_deleted(true);
        smartPhone.set_activated(false);
        phoneRepository.save(smartPhone);
    }

    @Override
    public void enable(Long id) {
        SmartPhone smartPhone = phoneRepository.getById(id);
        smartPhone.set_activated(true);
        smartPhone.set_deleted(false);
        phoneRepository.save(smartPhone);
    }

    @Override
    public Page<SmartPhone> pagePhone(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        Page<SmartPhone> pageSmartPhone = phoneRepository.pagePhone(pageable);
        return pageSmartPhone;
    }

    @Override
    public Page<SmartPhone> pageAllPhone(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 12);
        Page<SmartPhone> pageAllSmartPhone = phoneRepository.pageAllPhone(pageable);
        return pageAllSmartPhone;
    }

    @Override
    public List<SmartPhone> getRelatedProducts(Long categoryId) {
        return phoneRepository.getRelateProducts(categoryId);
    }
}
