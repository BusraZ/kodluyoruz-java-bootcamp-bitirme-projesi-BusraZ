package org.kodluyoruz.mybank.Transaction.SavingTransaction;

import lombok.*;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccount;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SavingTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String description;
    private String type;
    private String status;
    private double amount;
    private BigDecimal availableBalance;

   @ManyToOne
    @JoinColumn(name = "saving_account_id")
    private SavingAccount savingAccount;

    public SavingTransaction(Date date, String description, String type, String status, double amount, BigDecimal availableBalance,SavingAccount savingAccount) {
        this.date = date;
        this.description = description;
        this.type = type;
        this.status = status;
        this.amount = amount;
        this.availableBalance = availableBalance;
        this.savingAccount= savingAccount;
    }
}
