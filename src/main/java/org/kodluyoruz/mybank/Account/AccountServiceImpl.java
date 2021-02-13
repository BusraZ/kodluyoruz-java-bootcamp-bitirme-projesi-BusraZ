package org.kodluyoruz.mybank.Account;


import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccountDao;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccountDto;
import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccount;
import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccountDao;
import org.kodluyoruz.mybank.Customer.Customer;
import org.kodluyoruz.mybank.Customer.CustomerDao;
import org.kodluyoruz.mybank.Customer.CustomerService;
import org.kodluyoruz.mybank.Transaction.PrimaryTransaction;
import org.kodluyoruz.mybank.Transaction.SavingTransaction;
import org.kodluyoruz.mybank.Transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Date;


@Service
public class AccountServiceImpl implements AccountService {


   @Autowired
    private PrimaryAccountDao primaryAccountDao;
     @Autowired
    private SavingAccountDao savingAccountDao;

    @Autowired
    private CustomerService customerService;
 //   private CustomerDao customerDao;
    @Autowired
    private TransactionService transactionService;

    private static int nextAccountNumber = 10532671;
    private static long nextIban= 109326715;
    private int accountGen() {
        return ++nextAccountNumber;
    }
    private long accountIbanGen() {
        return ++nextIban;
    }
 /*   @Autowired
    private TransactionService transactionService; */
    @Override
    public PrimaryAccount createPrimaryAccount(Long id, PrimaryAccount primaryAccount, PrimaryAccount.Currency currency) {
 Customer customer= customerService.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : " + id));
        primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new BigDecimal(0.0));
        primaryAccount.setAccountNumber(accountGen());
        primaryAccount.setIban(accountIbanGen());
        customer.setPrimaryAccount(primaryAccount);
        primaryAccount.setCurrency(currency);
      primaryAccount.setCustomer(customer.toCustomerDto().toCustomer());

        return primaryAccountDao.save( primaryAccount) ;
    }

    @Override
    public PrimaryAccount getPrimaryAccount(long iban) {
        return primaryAccountDao.findByIban( iban);
    }
    public SavingAccount getSavingAccount(long iban){
        return savingAccountDao.findByIban(iban);
    }

    @Override
    public PrimaryAccount deletePrimaryAccount() {
        return null;
    }

    @Override
    public SavingAccount createSavingAccount(Long id, SavingAccount savingAccount, SavingAccount.Currency currency) {
        Customer customer = customerService.get(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found with id: " + id));
        savingAccount= new SavingAccount();
        savingAccount.setAccountBalance(new BigDecimal(0.0));
        savingAccount.setAccountNumber(accountGen());
        savingAccount.setIban(accountIbanGen());
        customer.setSavingAccount(savingAccount);
        savingAccount.setCurrency(currency);
        savingAccount.setCustomer(customer.toCustomerDto().toCustomer());


        return savingAccountDao.save(savingAccount);
    }

    @Override
    public SavingAccount deleteSavingAccount() {
        return null;
    }



    public void deposit(String accountType, double amount, long iban) {

        if (accountType.equalsIgnoreCase("Primary")) {
            PrimaryAccount primaryAccount= primaryAccountDao.findByIban(iban);
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);

            Date date = new Date();

           PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Deposit to Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance(), primaryAccount);
            transactionService.savePrimaryDepositTransaction(primaryTransaction);

        } else if (accountType.equalsIgnoreCase("Saving")) {
            SavingAccount  savingAccount = savingAccountDao.findByIban(iban);
            savingAccount.setAccountBalance(savingAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingAccountDao.save(savingAccount);

            Date date = new Date();
          SavingTransaction savingTransaction = new SavingTransaction(date, "Deposit to savings Account", "Account", "Finished", amount, savingAccount.getAccountBalance(), savingAccount);
            transactionService.saveSavingDepositTransaction(savingTransaction);
        }
    }

    public void withdraw(String accountType, double amount, long iban) {

        if (accountType.equalsIgnoreCase("Primary")) {
            PrimaryAccount primaryAccount= primaryAccountDao.findByIban(iban);
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);


            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Withdraw from Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance(), primaryAccount);
         transactionService.savePrimaryWithdrawTransaction(primaryTransaction);
        } else if (accountType.equalsIgnoreCase("Saving")) {
            SavingAccount  savingAccount = savingAccountDao.findByIban(iban);
            savingAccount.setAccountBalance(savingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingAccountDao.save(savingAccount);

            Date date = new Date();
           SavingTransaction savingsTransaction = new SavingTransaction(date, "Withdraw from savings Account", "Account", "Finished", amount, savingAccount.getAccountBalance(), savingAccount);
           transactionService.saveSavingWithdrawTransaction(savingsTransaction);
        }
    }


}
