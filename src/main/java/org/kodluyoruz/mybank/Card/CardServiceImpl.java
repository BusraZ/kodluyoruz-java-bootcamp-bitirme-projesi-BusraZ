package org.kodluyoruz.mybank.Card;

import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccountDao;
import org.kodluyoruz.mybank.Card.BankCard.BankCard;
import org.kodluyoruz.mybank.Card.BankCard.BankCardRepository;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCard;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCardRepository;
import org.kodluyoruz.mybank.Customer.Customer;
import org.kodluyoruz.mybank.Customer.CustomerService;
import org.kodluyoruz.mybank.Loan.Loan;
import org.kodluyoruz.mybank.Loan.LoanCheck;
import org.kodluyoruz.mybank.Loan.LoanRepository;
import org.kodluyoruz.mybank.Recipient.Recipient;
import org.kodluyoruz.mybank.Transaction.PrimaryTransaction.PrimaryTransaction;
import org.kodluyoruz.mybank.Transaction.PrimaryTransaction.PrimaryTransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CardServiceImpl implements CardService{

    @Autowired
    BankCardRepository bankCardRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    LoanRepository loanRepository;
    @Autowired
    PrimaryAccountDao primaryAccountDao;
    @Autowired
    CustomerService customerService;

    private static long bankcardNumber= 502007645;
    private static long cardNumber= 506207645;
    private static double credicardLimit= 1000;
    private static LocalDate expDate= LocalDate.of(2025,02,22);
    private static int cvv=183;

    public long bankcardGen(){
        return ++bankcardNumber;
    }
    public long cardGen(){
        return ++cardNumber;
    }

    @Override
    public BigDecimal credicardLimit() {
        return new BigDecimal(credicardLimit);
    }

    public LocalDate expDate(){
        return expDate;
    }
    public int cvv(){
        return ++cvv;
    }

    @Override
    public BankCard saveBankCard(BankCard bankCard) {
       return bankCardRepository.save(bankCard);
    }

    @Override
    public BankCard createBankCard(Long id) {
        Customer customer= customerService.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : " + id));
        PrimaryAccount primaryAccount1= customer.getPrimaryAccount();
        BankCard bankCard= new BankCard();
        bankCard.setCustomerId(customer);
        bankCard.setCvv(cvv());
       bankCard.setExpDate(expDate());
        bankCard.setNumber(cardGen());
        bankCard.setAccountNo(primaryAccount1);
        return bankCardRepository.save(bankCard);
    }

    @Override
    public CreditCard createCreditCard(Long id) {
        Customer customer= customerService.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : " + id));
        PrimaryAccount primaryAccount1= customer.getPrimaryAccount();
        CreditCard creditCard=new CreditCard();
        creditCard.setCustomerId(customer);
        creditCard.setCvv(cvv());
        creditCard.setCredicardLimit(credicardLimit());
        creditCard.setExpDate(expDate());
        creditCard.setNumber(cardGen());
        creditCard.setAccountNo(primaryAccount1);
       return creditCardRepository.save(creditCard);
    }

    @Override
    public CreditCard saveCreditCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    @Override
    public void crediCardPayment(Recipient recipient,  double amount, long cardNumber) {

            CreditCard creditCard= creditCardRepository.findByNumber(cardNumber);

              creditCard.setCredicardLimit(creditCard.getCredicardLimit().subtract(new BigDecimal(amount)));
              creditCardRepository.save(creditCard);
              Date date = new Date();

              Loan  loan= new Loan(date, "CreditCard Payment " + recipient.getName(),  "Finished",amount,(new BigDecimal(1000).subtract(creditCard.getCredicardLimit())), creditCard);


              loanRepository.save(loan);







    }

}
