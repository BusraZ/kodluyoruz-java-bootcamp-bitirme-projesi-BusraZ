package org.kodluyoruz.mybank.Recipient;

import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccountDao;
import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccountDao;
import org.kodluyoruz.mybank.Customer.Customer;
import org.kodluyoruz.mybank.Customer.CustomerService;
import org.kodluyoruz.mybank.Transaction.PrimaryTransaction.PrimaryTransactionDao;
import org.kodluyoruz.mybank.Transaction.SavingTransaction.SavingTransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipientServiceImpl implements RecipientService{
    @Autowired
    private CustomerService customerService;
    

    @Autowired
    RecipientRepository recipientRepository;
    @Override
    public List<Recipient> findRecipientList(Long id) {
        Customer customer = customerService.get(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found with id: " + id));
        String username =customer.getName();
        List<Recipient> recipientList = recipientRepository.findAll().stream()
                .filter(recipient -> username.equals(recipient.getCustomer().getName()))
                .collect(Collectors.toList());

        return recipientList;
    }

    @Override
    public Recipient saveRecipient(Recipient recipient) {
        return recipientRepository.save(recipient);
    }

    @Override
    public Recipient findRecipientByName(String recipientName) {
        return recipientRepository.findByName(recipientName);
    }

    @Override
    public void deleteRecipientByName(String recipientName) {
        recipientRepository.deleteByName(recipientName);
    }

}
