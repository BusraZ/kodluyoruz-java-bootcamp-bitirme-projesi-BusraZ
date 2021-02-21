package org.kodluyoruz.mybank.Transaction;

import org.kodluyoruz.mybank.Account.AccountServiceImpl;
import org.kodluyoruz.mybank.Customer.CustomerRepository;
import org.kodluyoruz.mybank.Customer.CustomerService;
import org.kodluyoruz.mybank.Recipient.Recipient;
import org.kodluyoruz.mybank.Recipient.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
public class TransactionController {


    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RecipientService recipientService;

    @PostMapping( "/{id}/betweenAccounts")
    public void betweenAccountsPost(
            @RequestParam("TransferType") String transferType,
            @RequestParam("transferFrom") long transferFrom,
            @RequestParam("transferTo") long transferTo,
            @RequestParam("amount") String amount

    ) throws Exception {
        transactionService.betweenAccountsTransfer(transferFrom, transferTo, amount,transferType );
    }
    @PostMapping("/toSomeoneElse")
    public void toSomeoneElsePost(@RequestParam("recipientName") String recipientName,
                                  @RequestParam("accountType") String accountType,
                                  @RequestParam("amount") double amount,
                                  @RequestParam("transferFrom") long transferFrom)throws Exception {

        Recipient recipient = recipientService.findRecipientByName(recipientName);
        transactionService.toSomeoneElseTransfer(recipient, accountType, amount, transferFrom);

    }
}
