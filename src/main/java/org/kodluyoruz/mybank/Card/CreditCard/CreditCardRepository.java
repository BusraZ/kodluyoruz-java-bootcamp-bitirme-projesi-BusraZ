package org.kodluyoruz.mybank.Card.CreditCard;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {
    CreditCard findByNumber(long number);
}
