package org.kodluyoruz.mybank.Card;

import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Card.BankCard.BankCard;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCard;
import org.kodluyoruz.mybank.Customer.Customer;
import org.kodluyoruz.mybank.Customer.CustomerService;
import org.kodluyoruz.mybank.Recipient.Recipient;
import org.kodluyoruz.mybank.Recipient.RecipientService;
import org.kodluyoruz.mybank.Transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/Card")
public class CardController {
    @Autowired
    RecipientService recipientService;
    @Autowired
    CardService cardService;
    @Autowired
    CustomerService customerService;

    @PostMapping("/crediCardPayment")
    public void toSomeoneElsePost(@RequestParam("recipientName") String recipientName,
                                  @RequestParam("amount") double amount,
                                  @RequestParam("customer") long cardnumber) {

        Recipient recipient = recipientService.findRecipientByName(recipientName);
       cardService.crediCardPayment(recipient, amount, cardnumber);
    }

    @PostMapping("/{id}/new/creditCard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard create(@PathVariable("id")Long id) {

     return   cardService.createCreditCard(id);

    }
    @PostMapping("/{id}/new/bankCard")
    @ResponseStatus(HttpStatus.CREATED)
    public BankCard createCard(@PathVariable("id")Long id) {

        return   cardService.createBankCard(id);

    }

}
