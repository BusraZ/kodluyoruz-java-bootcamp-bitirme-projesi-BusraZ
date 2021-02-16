package org.kodluyoruz.mybank.Card.CreditCard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Customer.Customer;
import org.kodluyoruz.mybank.Loan.Loan;
import org.kodluyoruz.mybank.Transaction.PrimaryTransaction.PrimaryTransaction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long number;
    private LocalDate expDate;
    private int cvv;
    private BigDecimal credicardLimit;
    @OneToOne
    @JoinColumn(name = "accountNo",referencedColumnName = "id")
    private PrimaryAccount accountNo;
    @OneToOne
    @JoinColumn(name = "customerId",referencedColumnName = "Id")
    private Customer customerId;

    /*@OneToMany(mappedBy = "creditcard")
    @JsonIgnore
    private List<Loan> loanList;*/

    public CreditCard(long cardNumber,LocalDate expDate,int cvv,PrimaryAccount accountNo,Customer customerId,BigDecimal credicardLimit){
        this.number=cardNumber;
        this.expDate=expDate;
        this.cvv=cvv;
        this.accountNo=accountNo;
        this.customerId=customerId;
        this.credicardLimit=credicardLimit;
    }
}
