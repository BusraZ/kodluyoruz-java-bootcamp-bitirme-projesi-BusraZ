package org.kodluyoruz.mybank.Loan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCard;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanCheck {
    private BigDecimal sumLoan;
    CreditCard creditCard;
}
