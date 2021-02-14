package org.kodluyoruz.mybank.Card;

import org.kodluyoruz.mybank.Card.BankCard.BankCard;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCard;

import java.time.LocalDate;

public interface CardService {
   long cardNumber();
   long credicardNumber();
   double credicardLimit();
   LocalDate expDate();
   int cvv();
   void saveBankCard(BankCard bankCard);
   void saveCreditCard(CreditCard creditCard);

  // void saveSavingDepositTransaction(SavingTransaction savingsTransaction);
}
