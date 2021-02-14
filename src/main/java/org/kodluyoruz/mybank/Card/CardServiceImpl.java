package org.kodluyoruz.mybank.Card;

import org.kodluyoruz.mybank.Card.BankCard.BankCard;
import org.kodluyoruz.mybank.Card.BankCard.BankCardRepository;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCard;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCardRepository;
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

    private static long cardnumber= 502007645;
    private static long credicardnumber= 506207645;
    private static double credicardLimit=1000;
    private static LocalDate expDate= LocalDate.of(2025,02,22);
    private static int cvv=183;

    public long cardNumber(){
        return ++cardnumber;
    }
    public long credicardNumber(){
        return ++credicardnumber;
    }

    @Override
    public double credicardLimit() {
        return credicardLimit;
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

}
