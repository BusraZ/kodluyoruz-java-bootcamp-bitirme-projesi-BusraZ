package org.kodluyoruz.mybank.Account.SavingAccount;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class SavingAccountDto {


        private Long id;
        private int accountNumber;
        private BigDecimal accountBalance;
        private long Iban;
        // private Customer customer;
        // private List<PrimaryTransaction> primaryTransactionList;

 /*  public SavingAccount toSavingAccount(){
        return SavingAccount.builder().id(this.id).
                accountNumber(this.accountNumber).accountBalance(this.accountBalance).Iban(this.Iban).customer(this.customer).build();
    } */
}
