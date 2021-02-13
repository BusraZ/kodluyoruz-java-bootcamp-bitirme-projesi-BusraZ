package org.kodluyoruz.mybank.Account.PrimaryAccount;

import lombok.Builder;
import lombok.Data;
import org.kodluyoruz.mybank.Customer.Customer;
import org.kodluyoruz.mybank.Transaction.PrimaryTransaction;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class PrimaryAccountDto {
    private Long id;
    private int accountNumber;
    private BigDecimal accountBalance;
    private long Iban;
    private Currency currency;


    public enum Currency{
        USD,
        EUR,
        TRY
    }
  //  private Customer customer;
   // private List<PrimaryTransaction> primaryTransactionList;

   /* public PrimaryAccount toPrimaryAccount(){
        return PrimaryAccount.builder().id(this.id).
                accountNumber(this.accountNumber).accountBalance(this.accountBalance).Iban(this.Iban).customer(this.customer).build();
    } */
}
