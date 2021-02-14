package org.kodluyoruz.mybank.Transaction;

import org.kodluyoruz.mybank.Account.AccountService;
import org.kodluyoruz.mybank.Account.AccountServiceImpl;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccount;
import org.kodluyoruz.mybank.Customer.Customer;
import org.kodluyoruz.mybank.Customer.CustomerDao;
import org.kodluyoruz.mybank.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransactionController {


    @Autowired
    private TransactionService transactionService;
     @Autowired
     CustomerDao customerDao;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @PostMapping( "/{id}/betweenAccounts")
    public void betweenAccountsPost(
            @RequestParam("TransferType") String transferType,
            @RequestParam("transferFrom") long transferFrom,
            @RequestParam("transferTo") long transferTo,
            @RequestParam("amount") String amount

    ) throws Exception {
        transactionService.betweenAccountsTransfer(transferFrom, transferTo, amount,transferType );
    }
}
