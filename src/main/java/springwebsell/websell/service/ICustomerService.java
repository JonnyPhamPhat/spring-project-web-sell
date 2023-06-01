package springwebsell.websell.service;

import springwebsell.websell.model.Customer;

public interface ICustomerService {
    Customer findByUsername(String username);
}
