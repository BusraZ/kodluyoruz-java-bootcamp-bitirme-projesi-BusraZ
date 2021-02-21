package org.kodluyoruz.mybank.Customer;

import lombok.RequiredArgsConstructor;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccountDao;
import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccountDao;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
   private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        Customer c = new Customer();
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Long id,Customer customer) {

        return customerRepository.findById(id)
                .map(x -> {
                    x.setName(customer.getName());
                    x.setSurname(customer.getSurname());
                    return customerRepository.save(x);
                })
                .orElseGet(() -> {
                    customer.setId(id);
                    return customerRepository.save(customer);
                });
    }


    @Override
    public void deleteCustomerById(Long id) {
       Customer customer= customerRepository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : " + id));
       if( ( customer.getPrimaryAccount()==null || customer.getPrimaryAccount().getAccountBalance().equals(0)) && (customer.getSavingAccount()==null || customer.getSavingAccount().equals(0)) &&
               ( customer.getCreditCard()==null || customer.getCreditCard().getCredicardLimit().equals(1000)) ){
         this.customerRepository.deleteById(id);
      }
    }
    public Optional<Customer> get(Long id) {
        return customerRepository.findById(id);
    }

   }
