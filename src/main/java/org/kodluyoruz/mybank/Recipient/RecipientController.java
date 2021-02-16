package org.kodluyoruz.mybank.Recipient;

import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Customer.Customer;
import org.kodluyoruz.mybank.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(params = {"id", "page", "size"})
    public List<Recipient> findDene(@RequestParam("id") Long id, @Min(value = 0) @RequestParam("page") int page, @RequestParam("size") int size) {
        return  recipientService.findRecipientList(id, PageRequest.of(page, size)).stream()
                .collect(Collectors.toList());
    }
}
