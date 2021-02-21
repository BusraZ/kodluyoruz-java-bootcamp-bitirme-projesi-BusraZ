package org.kodluyoruz.mybank.Customer;

import java.util.Optional;

public interface CustomerService {


    Customer create(Customer customer);
    Customer update(Long id,Customer customer);
    void deleteCustomerById(Long id);
    Optional<Customer> get(Long id);
}
