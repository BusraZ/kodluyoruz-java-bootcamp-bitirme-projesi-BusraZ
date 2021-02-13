package org.kodluyoruz.mybank.Account.SavingAccount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kodluyoruz.mybank.Customer.Customer;
import org.kodluyoruz.mybank.Transaction.SavingTransaction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SavingAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int accountNumber;
    private BigDecimal accountBalance;
    private long iban;
    @Enumerated(value = EnumType.STRING)
    private Currency currency;
    @OneToOne(mappedBy = "savingAccount")
    private Customer customer;

   public enum Currency{
        USD,
        EUR,
        TRY
    }
  /* @OneToMany(mappedBy = "savingsAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SavingTransaction> savingsTransactionList; */

    /* public SavingAccountDto toSavingAccountDto(){
        return SavingAccountDto.builder().id(this.id).
                accountNumber(this.accountNumber).
                accountBalance(this.accountBalance).Iban(this.Iban).customer(this.customer).build();
    } */
}
