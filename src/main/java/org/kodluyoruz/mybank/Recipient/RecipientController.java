package org.kodluyoruz.mybank.Recipient;

import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Customer.Customer;
import org.kodluyoruz.mybank.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/recipient")
public class RecipientController {
    @Autowired
    CustomerService customerService;
    @Autowired
    RecipientService recipientService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Recipient recipientPost(@PathVariable("id")Long id, @Valid @RequestBody Recipient recipient) {

        Customer customer= customerService.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : " + id));
        recipient.setCustomer(customer);
      return   recipientService.saveRecipient(recipient);
    }
}
