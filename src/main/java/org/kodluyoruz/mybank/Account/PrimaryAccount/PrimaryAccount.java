package org.kodluyoruz.mybank.Account.PrimaryAccount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.kodluyoruz.mybank.Card.BankCard.BankCard;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCard;
import org.kodluyoruz.mybank.Customer.Customer;
import org.kodluyoruz.mybank.Transaction.PrimaryTransaction.PrimaryTransaction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PrimaryAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int accountNumber;
    private BigDecimal accountBalance;
    private long iban;
    @Enumerated(value = EnumType.STRING)
    private Currency currency;
    @OneToOne(mappedBy = "primaryAccount")
    private Customer customer;
    @OneToOne(mappedBy = "accountNo")
    CreditCard creditCard;
    @OneToOne(mappedBy = "accountNo")
    BankCard bankCard;
   public enum Currency{
       USD,
       EUR,
        TRY
    }
    public enum Card{
        Bank,
        Credit
    }
    @OneToMany(mappedBy = "primaryAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PrimaryTransaction> primaryTransactionList;

  /*  public PrimaryAccountDto toPrimaryAccountDto(){
        return PrimaryAccountDto.builder().id(this.id).
                accountNumber(this.accountNumber).
                accountBalance(this.accountBalance).Iban(this.Iban).customer(this.customer).build();
    } */
}
