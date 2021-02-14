package org.kodluyoruz.mybank.Card.CreditCard;

import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Customer.Customer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long cardNumber;
    private LocalDate expDate;
    private int cvv;
    private double credicardLimit;
    @OneToOne
    @JoinColumn(name = "accountNo",referencedColumnName = "accountNumber")
    private PrimaryAccount accountNo;
    @OneToOne
    @JoinColumn(name = "customerId",referencedColumnName = "Id")
    private Customer customerId;

    public CreditCard(long cardNumber,LocalDate expDate,int cvv,PrimaryAccount accountNo,Customer customerId,double credicardLimit){
        this.cardNumber=cardNumber;
        this.expDate=expDate;
        this.cvv=cvv;
        this.accountNo=accountNo;
        this.customerId=customerId;
        this.credicardLimit=credicardLimit;
    }
}
