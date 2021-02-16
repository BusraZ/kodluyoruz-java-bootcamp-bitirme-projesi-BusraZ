package org.kodluyoruz.mybank.Card.BankCard;

import org.kodluyoruz.mybank.Card.CreditCard.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankCardRepository extends JpaRepository<BankCard,Long> {
   BankCard findByNumber(long number);
}
