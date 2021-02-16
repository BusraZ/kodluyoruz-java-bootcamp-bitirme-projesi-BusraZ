package org.kodluyoruz.mybank.Loan;

import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccountDao;
import org.kodluyoruz.mybank.Card.BankCard.BankCard;
import org.kodluyoruz.mybank.Card.BankCard.BankCardRepository;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCard;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCardRepository;
import org.kodluyoruz.mybank.Customer.Customer;
import org.kodluyoruz.mybank.Recipient.Recipient;
import org.kodluyoruz.mybank.Transaction.PrimaryTransaction.PrimaryTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.net.CacheRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService{
   @Autowired
    CreditCardRepository creditCardRepository;
   @Autowired
    BankCardRepository bankCardRepository;
  @Autowired
    LoanRepository loanRepository;
  @Autowired
    PrimaryAccountDao primaryAccountDao;

    /*
      public Page<Loan> list(long cardnumber, Pageable pageable) {
        return loanRepository.findLoanList(cardnumber,pageable);

      }*/
    @Override
  public List<Loan> findBulLoanList(long cardnumber, Pageable pageable) {
        CreditCard creditCard= creditCardRepository.findByNumber(cardnumber);
        List<Loan> loanList = loanRepository.findAll().stream()
                .filter(a-> creditCard.getId().equals(a.getCreditCard().getId()))
                .collect(Collectors.toList());

        return loanList;
    }

    @Override
    public BigDecimal check(long cardnumber) {
        CreditCard creditCard= creditCardRepository.findByNumber(cardnumber);
        LoanCheck deger= new LoanCheck((new BigDecimal(1000).subtract(creditCard.getCredicardLimit())),creditCard) ;
        return deger.getSumLoan();
    }

    @Override
    public void payLoanwithCard(long cardnumber,double amount)throws Exception{
        BankCard bankCard = bankCardRepository.findByNumber(cardnumber);
      PrimaryAccount primaryAccount= bankCard.getAccountNo();
        if( primaryAccount.getAccountBalance().compareTo(new BigDecimal(amount)) >=0){
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            CreditCard creditCard= primaryAccount.getCreditCard();
            creditCard.setCredicardLimit(creditCard.getCredicardLimit().add(new BigDecimal(amount)));
            creditCardRepository.save(creditCard);
            Date date = new Date();

            Loan  loan= new Loan(date, "CreditCard loan pay with Account",  "Finished",amount,(new BigDecimal(1000).subtract(creditCard.getCredicardLimit())), creditCard);

            loanRepository.save(loan);
        }
        else {
            throw new Exception("Not enough balance");
        }
    }

    @Override
    public void payLoanwithAccount(long iban,double amount)throws Exception  {
        PrimaryAccount primaryAccount= primaryAccountDao.findByIban(iban);
       if( primaryAccount.getAccountBalance().compareTo(new BigDecimal(amount)) >=0){
           primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
           primaryAccountDao.save(primaryAccount);
           CreditCard creditCard= primaryAccount.getCreditCard();
           creditCard.setCredicardLimit(creditCard.getCredicardLimit().add(new BigDecimal(amount)));
           creditCardRepository.save(creditCard);
          Date date = new Date();

           Loan  loan= new Loan(date, "CreditCard loan pay with Account",  "Finished",amount,(new BigDecimal(1000).subtract(creditCard.getCredicardLimit())), creditCard);

           loanRepository.save(loan);
          }
       else {
           throw new Exception("Not enough balance");
           }

    }

}
