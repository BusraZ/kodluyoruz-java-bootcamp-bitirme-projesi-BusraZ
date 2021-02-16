package org.kodluyoruz.mybank.Customer;

import lombok.RequiredArgsConstructor;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccountDao;
import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccount;
import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccountDao;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
   private final CustomerDao customerDao;
   private final PrimaryAccountDao primaryAccountDao;
   private final SavingAccountDao savingAccountDao;
    @Override
    public Customer create(Customer customer) {

        return customerDao.save(customer);
    }

    @Override
    public Customer update(Long id,Customer customer) {

        return customerDao.findById(id)
                .map(x -> {
                    x.setName(customer.getName());
                    x.setSurname(customer.getSurname());
                    return customerDao.save(x);
                })
                .orElseGet(() -> {
                    customer.setId(id);
                    return customerDao.save(customer);
                });
    }

@Override
public Customer findByName(String name){
        return  customerDao.findByName(name);
}
    @Override
    public void deleteCustomerById(Long id) {
       Customer customer= customerDao.findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : " + id));
       if( ( customer.getPrimaryAccount()==null || customer.getPrimaryAccount().getAccountBalance().equals(0)) && (customer.getSavingAccount()==null || customer.getSavingAccount().equals(0)) &&
               ( customer.getCreditCard()==null || customer.getCreditCard().getCredicardLimit().equals(1000)) ){
         this.customerDao.deleteById(id);
      }
    }
    public Optional<Customer> get(Long id) {
        return customerDao.findById(id);
    }

    @Override
    public Customer findByPrimaryAccount_Iban(long iban) {
        return customerDao.findByPrimaryAccount_Iban(iban);
    }
    @Override
    public Customer findBySavingAccount_Iban(long iban) {
        return customerDao.findBySavingAccount_Iban(iban);
    }
}
