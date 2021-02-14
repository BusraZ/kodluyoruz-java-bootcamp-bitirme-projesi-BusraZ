package org.kodluyoruz.mybank.Card.CreditCard;

import lombok.*;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Customer.Customer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long cardNumber;
    private LocalDate expDate;
    private int cvv;
    private BigDecimal credicardLimit;
    @OneToOne
    @JoinColumn(name = "accountNo",referencedColumnName = "accountNumber")
    private PrimaryAccount accountNo;
    @OneToOne
    @JoinColumn(name = "customerId",referencedColumnName = "Id")
    private Customer customerId;

    public CreditCard(long cardNumber,LocalDate expDate,int cvv,PrimaryAccount accountNo,Customer customerId,BigDecimal credicardLimit){
        this.cardNumber=cardNumber;
        this.expDate=expDate;
        this.cvv=cvv;
        this.accountNo=accountNo;
        this.customerId=customerId;
        this.credicardLimit=credicardLimit;
    }
}
