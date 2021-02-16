package org.kodluyoruz.mybank.Customer;

import java.util.Optional;

public interface CustomerService {

  //  CustomerDto create(CustomerDto customerDto);
    Customer create(Customer customer);
Customer findByName(String  name);
    Customer update(Long id,Customer customer);
    void deleteCustomerById(Long id);
    Optional<Customer> get(Long id);
    Customer findByPrimaryAccount_Iban(long iban);
    Customer findBySavingAccount_Iban(long iban);
}
