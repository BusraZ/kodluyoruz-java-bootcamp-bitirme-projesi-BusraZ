package org.kodluyoruz.mybank.Transaction;

import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccountDao;
import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccount;
import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccountDao;
import org.kodluyoruz.mybank.Customer.Customer;
import org.kodluyoruz.mybank.Customer.CustomerService;
import org.kodluyoruz.mybank.Recipient.Recipient;
import org.kodluyoruz.mybank.Recipient.RecipientRepository;
import org.kodluyoruz.mybank.Transaction.PrimaryTransaction.PrimaryTransaction;
import org.kodluyoruz.mybank.Transaction.PrimaryTransaction.PrimaryTransactionDao;
import org.kodluyoruz.mybank.Transaction.SavingTransaction.SavingTransaction;
import org.kodluyoruz.mybank.Transaction.SavingTransaction.SavingTransactionDao;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final RestTemplate restTemplate;


    public TransactionServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri("https://api.exchangeratesapi.io").build();
    }

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PrimaryTransactionDao primaryTransactionDao;

    @Autowired
    private SavingTransactionDao savingTransactionDao;
    @Autowired
    private PrimaryAccountDao primaryAccountDao;

    @Autowired
    private SavingAccountDao savingAccountDao;

    @Autowired
    RecipientRepository recipientRepository;


    public List<PrimaryTransaction> findPrimaryTransactionList(Long id) {
        Customer customer = customerService.get(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found with id: " + id));

        List<PrimaryTransaction> primaryTransactionList = customer.getPrimaryAccount().getPrimaryTransactionList();

        return primaryTransactionList;
    }

    public List<SavingTransaction> findSavingTransactionList(Long id) {
        Customer customer = customerService.get(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found with id: " + id));

           List<SavingTransaction> savingTransactionList = customer.getSavingAccount().getSavingTransactionList();

        return savingTransactionList;

    }

    public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {
        primaryTransactionDao.save(primaryTransaction);
    }

    public void saveSavingDepositTransaction(SavingTransaction savingTransaction) {
        savingTransactionDao.save(savingTransaction);
    }

    public void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction) {

        primaryTransactionDao.save(primaryTransaction);
    }

    public void saveSavingWithdrawTransaction(SavingTransaction savingTransaction) {
        savingTransactionDao.save(savingTransaction);
    }

    public void betweenAccountsTransfer(long transferFrom, long transferTo, String amount,String transferType) throws Exception {

        if(transferType.equalsIgnoreCase("Primary")){
            PrimaryAccount primaryAccount= primaryAccountDao.findByIban(transferFrom);
            SavingAccount  savingAccount = savingAccountDao.findByIban(transferTo);
            PrimaryAccount.Currency currencyfrom = primaryAccount.getCurrency();
            SavingAccount.Currency currencyto = savingAccount.getCurrency();
          if(currencyto.toString().equalsIgnoreCase(currencyfrom.toString())==false) {
              CurrencyRate result = restTemplate.getForObject("/latest?base={currencyfrom}", CurrencyRate.class, currencyfrom.toString());
              Double rate = result.getRates().get(currencyto.toString());
              double amount1 = Double.parseDouble(amount);
              double amount2 = amount1 * rate;
              savingAccount.setAccountBalance(savingAccount.getAccountBalance().add(new BigDecimal(amount2)));
               }
          else {
              savingAccount.setAccountBalance(savingAccount.getAccountBalance().add(new BigDecimal(amount)));
              }
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            savingAccountDao.save(savingAccount);
            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Between account transfer from "+ transferFrom +" to " + transferTo , "Account", "Finished", Double.parseDouble(amount), primaryAccount.getAccountBalance(), primaryAccount);
            primaryTransactionDao.save(primaryTransaction);
       } else if (transferType.equalsIgnoreCase("Saving")) {
            SavingAccount  savingAccount = savingAccountDao.findByIban(transferFrom);
            PrimaryAccount primaryAccount= primaryAccountDao.findByIban(transferTo);
            PrimaryAccount.Currency currencyto = primaryAccount.getCurrency();
            SavingAccount.Currency  currencyfrom  = savingAccount.getCurrency();
            if(currencyto.toString().equalsIgnoreCase(currencyfrom.toString())==false) {
                CurrencyRate result = restTemplate.getForObject("/latest?base={currencyfrom}", CurrencyRate.class, currencyfrom);
                Double rate = result.getRates().get(currencyto.toString());
                double amount1 = Double.parseDouble(amount);
                double amount2 = amount1 * rate;
                primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount2)));
            }
            else {
                primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            }

            savingAccount.setAccountBalance(savingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            savingAccountDao.save(savingAccount);

            Date date = new Date();

            SavingTransaction savingTransaction = new SavingTransaction(date, "Between account transfer from " + transferFrom + " to " + transferTo , "Transfer", "Finished", Double.parseDouble(amount), savingAccount.getAccountBalance(), savingAccount);
            savingTransactionDao.save(savingTransaction);
        } else {
            throw new Exception("Invalid Transfer");
       }

    }


    @Override
    public void toSomeoneElseTransfer(Recipient recipient, String accountType, double amount, long transferFrom) throws Exception{
        if (accountType.equalsIgnoreCase("Primary")) {
            PrimaryAccount primaryAccount= primaryAccountDao.findByIban(transferFrom);
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Transfer to recipient " + recipient.getName(), "Transfer", "Finished",amount, primaryAccount.getAccountBalance(), primaryAccount);
            primaryTransactionDao.save(primaryTransaction);
        } else {
            throw new Exception(" Transfer must make with Primary Account");
         }
    }

}
