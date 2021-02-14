package org.kodluyoruz.mybank.Card;

import org.kodluyoruz.mybank.Recipient.Recipient;
import org.kodluyoruz.mybank.Recipient.RecipientService;
import org.kodluyoruz.mybank.Transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Card")
public class CardController {
    @Autowired
    RecipientService recipientService;
    @Autowired
    CardService cardService;

    @PostMapping("/crediCardPayment")
    public void toSomeoneElsePost(@RequestParam("recipientName") String recipientName,
                                  @RequestParam("amount") double amount,
                                  @RequestParam("transferFrom") long cardnumber) {

        Recipient recipient = recipientService.findRecipientByName(recipientName);
       cardService.crediCardPayment(recipient, amount, cardnumber);
    }

}
