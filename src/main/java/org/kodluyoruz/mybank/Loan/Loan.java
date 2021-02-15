package org.kodluyoruz.mybank.Loan;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCard;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Loan {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Date date;
        private String description;
        private String status;
        private double amount;
        private BigDecimal sumLoan= new BigDecimal(0);
        private BigDecimal availableBalance;
        @ManyToOne
        @JoinColumn(name = "creditcard",referencedColumnName = "id")
        private CreditCard creditCard;

    public Loan(Date date, String description, String status, double amount,BigDecimal availableBalance, CreditCard creditCard) {
        this.date = date;
        this.description = description;
        this.status = status;
        this.amount = amount;
        this.availableBalance=availableBalance;
        this.creditCard=creditCard;
    }

}
