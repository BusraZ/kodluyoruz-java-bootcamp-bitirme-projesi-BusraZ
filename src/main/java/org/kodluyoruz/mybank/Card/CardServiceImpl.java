package org.kodluyoruz.mybank.Card;

import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccountDao;
import org.kodluyoruz.mybank.Card.BankCard.BankCard;
import org.kodluyoruz.mybank.Card.BankCard.BankCardRepository;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCard;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCardRepository;
import org.kodluyoruz.mybank.Recipient.Recipient;
import org.kodluyoruz.mybank.Transaction.PrimaryTransaction.PrimaryTransaction;
import org.kodluyoruz.mybank.Transaction.PrimaryTransaction.PrimaryTransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Service
public class CardServiceImpl implements CardService{

    @Autowired
    BankCardRepository bankCardRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    PrimaryTransactionDao primaryTransactionDao;
    @Autowired
    PrimaryAccountDao primaryAccountDao;

    private static long cardnumber= 502007645;
    private static long credicardnumber= 506207645;
    private static double credicardLimit= 1000;
    private static LocalDate expDate= LocalDate.of(2025,02,22);
    private static int cvv=183;

    public long cardNumber(){
        return ++cardnumber;
    }
    public long credicardNumber(){
        return ++credicardnumber;
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
    public void saveBankCard(BankCard bankCard) {
        bankCardRepository.save(bankCard);
    }

    @Override
    public void saveCreditCard(CreditCard creditCard) {
        creditCardRepository.save(creditCard);
    }

    @Override
    public void crediCardPayment(Recipient recipient,  double amount, long cardNumber) {

           CreditCard creditCard = creditCardRepository.findByCardNumber(cardNumber);
           creditCard.setCredicardLimit(creditCard.getCredicardLimit().subtract(new BigDecimal(amount)));
           creditCardRepository.save(creditCard);

        int a= creditCard.getAccountNo().getAccountNumber();
        PrimaryAccount primaryAccount = primaryAccountDao.findByAccountNumber(a);
        primaryAccountDao.save(primaryAccount);
           Date date = new Date();

           PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Transfer to recipient " + recipient.getName(), "Transfer", "Finished", amount, creditCard.getCredicardLimit(),primaryAccount);
           primaryTransactionDao.save(primaryTransaction);

    }

}
