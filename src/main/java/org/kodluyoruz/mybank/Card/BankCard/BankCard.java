package org.kodluyoruz.mybank.Card.BankCard;

import lombok.Data;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Customer.Customer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class BankCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long cardNumber;
    private LocalDate expDate;
    private int cvv;
    private double amount;
    @OneToOne
    @JoinColumn(name = "accountNo",referencedColumnName = "accountNumber")
    private PrimaryAccount accountNo;
    @OneToOne
    @JoinColumn(name = "customerId",referencedColumnName = "Id")
    private Customer customerId;

    public BankCard(long cardNumber,LocalDate expDate,int cvv,PrimaryAccount accountNo,Customer customerId,double amount){
        this.cardNumber=cardNumber;
        this.expDate=expDate;
        this.cvv=cvv;
        this.accountNo=accountNo;
        this.amount=amount;
        this.customerId=customerId;
    }



}
