package org.kodluyoruz.mybank.Transaction;

import org.kodluyoruz.mybank.Account.AccountService;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccountDao;
import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccount;
import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccountDao;
import org.kodluyoruz.mybank.Customer.Customer;
import org.kodluyoruz.mybank.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

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


 //   @Autowired
  //  private RecipientDao recipientDao;


    public List<PrimaryTransaction> findPrimaryTransactionList(String name) {
        Customer customer = customerService.findByName(name);
        List<PrimaryTransaction> primaryTransactionList = customer.getPrimaryAccount().getPrimaryTransactionList();

        return primaryTransactionList;
    }

    public List<SavingTransaction> findSavingTransactionList(String name) {
        Customer customer = customerService.findByName(name);
    //    List<SavingTransaction> savingTransactionList = customer.getSavingAccount().getSavingTransactionList();

       // return savingTransactionList;
        return null;
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
      /* Object parameter = primaryAccount.getCurrency();*/


            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingAccount.setAccountBalance(savingAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            savingAccountDao.save(savingAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Between account transfer from "+ transferFrom +" to " + transferTo , "Account", "Finished", Double.parseDouble(amount), primaryAccount.getAccountBalance(), primaryAccount);
            primaryTransactionDao.save(primaryTransaction);
       } else if (transferType.equalsIgnoreCase("Saving")) {
            SavingAccount  savingAccount = savingAccountDao.findByIban(transferFrom);
            PrimaryAccount primaryAccount= primaryAccountDao.findByIban(transferTo);
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
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
}
