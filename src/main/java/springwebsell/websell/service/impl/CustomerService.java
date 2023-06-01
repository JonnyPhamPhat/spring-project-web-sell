package springwebsell.websell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springwebsell.websell.model.Customer;
import springwebsell.websell.repository.CustomerRepository;
import springwebsell.websell.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }
}
