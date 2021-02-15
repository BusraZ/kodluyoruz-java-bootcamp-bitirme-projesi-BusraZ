package org.kodluyoruz.mybank.Card;

import org.kodluyoruz.mybank.Card.BankCard.BankCard;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCard;
import org.kodluyoruz.mybank.Recipient.Recipient;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CardService {
   long bankcardGen();
   long cardGen();
   BigDecimal credicardLimit();
   LocalDate expDate();
   int cvv();
   void saveBankCard(BankCard bankCard);
   void saveCreditCard(CreditCard creditCard);

  void crediCardPayment(Recipient recipient, double amount,long number);

  // void saveSavingDepositTransaction(SavingTransaction savingsTransaction);
}
