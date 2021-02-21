package org.kodluyoruz.mybank.Account;


import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccountDao;
import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccount;
import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccountDao;
import org.kodluyoruz.mybank.Card.BankCard.BankCard;
import org.kodluyoruz.mybank.Card.CardService;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCard;
import org.kodluyoruz.mybank.Customer.Customer;
import org.kodluyoruz.mybank.Customer.CustomerService;
import org.kodluyoruz.mybank.Transaction.PrimaryTransaction.PrimaryTransaction;
import org.kodluyoruz.mybank.Transaction.SavingTransaction.SavingTransaction;
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
private CardService cardService;
   @Autowired
    private PrimaryAccountDao primaryAccountDao;
     @Autowired
    private SavingAccountDao savingAccountDao;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;

    private static int nextAccountNumber = 1053224;
    private static long nextIban= 109323715;
    private int accountGen() {
        return ++nextAccountNumber;
    }
    private long accountIbanGen() {
        return ++nextIban;
    }

    @Override
    public PrimaryAccount createPrimaryAccount(Long id, PrimaryAccount primaryAccount, PrimaryAccount.Currency currency,PrimaryAccount.Card cardType) {
 Customer customer= customerService.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : " + id));
        primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new BigDecimal(0.0));
        primaryAccount.setAccountNumber(accountGen());
        primaryAccount.setIban(accountIbanGen());
        customer.setPrimaryAccount(primaryAccount);
        primaryAccount.setCurrency(currency);
        primaryAccount.setCustomer(customer.toCustomerDto().toCustomer());
        primaryAccountDao.save( primaryAccount);
        if (cardType.toString().equalsIgnoreCase("Bank")) {
        BankCard bankCard= new BankCard(cardService.bankcardGen(),cardService.expDate(),cardService.cvv(),primaryAccount,customer,0);
        cardService.saveBankCard(bankCard);
        }else if (cardType.toString().equalsIgnoreCase("Credit")) {
        CreditCard creditCard= new CreditCard(cardService.cardGen(),cardService.expDate(),cardService.cvv(),primaryAccount,customer,cardService.credicardLimit());
        cardService.saveCreditCard(creditCard);}
        return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber()) ;
    }

    @Override
    public PrimaryAccount getPrimaryAccount(long iban) {
        return primaryAccountDao.findByIban( iban);
    }
    public SavingAccount getSavingAccount(long iban){
        return savingAccountDao.findByIban(iban);
    }

    @Override
    public void deletePrimaryAccount(Long id)throws Exception {
        PrimaryAccount primaryAccount= primaryAccountDao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with id : " + id));;
      if(primaryAccount.getAccountBalance().equals(0)){
          this.primaryAccountDao.deleteById(id);
      }
      else {
          throw new Exception("AccountBalance is not empty");
      }

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
    public void deleteSavingAccount(Long id) throws Exception{
      SavingAccount savingAccount=savingAccountDao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with id : " + id));;
        if(savingAccount.getAccountBalance().equals(0)){
            this.savingAccountDao.deleteById(id);
         }
        else {
            throw new Exception("AccountBalance is not empty");
        }

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

    public void withdraw(String accountType, double amount, long iban) throws Exception {

        if (accountType.equalsIgnoreCase("Primary")) {
            PrimaryAccount primaryAccount= primaryAccountDao.findByIban(iban);
          if(primaryAccount.getAccountBalance().compareTo(new BigDecimal(amount)) >=0 ) {
              primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
              primaryAccountDao.save(primaryAccount);


              Date date = new Date();

              PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Withdraw from Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance(), primaryAccount);
              transactionService.savePrimaryWithdrawTransaction(primaryTransaction);
          }
          else {
              throw new Exception("Not enough balance");
           }

        } else if (accountType.equalsIgnoreCase("Saving")) {
            SavingAccount  savingAccount = savingAccountDao.findByIban(iban);
            if(savingAccount.getAccountBalance().compareTo(new BigDecimal(amount))>=0){
            savingAccount.setAccountBalance(savingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingAccountDao.save(savingAccount);

            Date date = new Date();
           SavingTransaction savingsTransaction = new SavingTransaction(date, "Withdraw from savings Account", "Account", "Finished", amount, savingAccount.getAccountBalance(), savingAccount);
           transactionService.saveSavingWithdrawTransaction(savingsTransaction);
            }
            else{
                throw new Exception("Not enough balance");
            }
        }
    }


}
