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
    @OneToOne
    @JoinColumn(name = "primaryAccountId",referencedColumnName = "id")
    private PrimaryAccount primaryAccountId;
    @OneToOne
    @JoinColumn(name = "customerId",referencedColumnName = "Id")
    private Customer customerId;

    public BankCard(long cardNumber,LocalDate expDate,int cvv,PrimaryAccount primaryAccountId,Customer customerId){
        this.cardNumber=cardNumber;
        this.expDate=expDate;
        this.cvv=cvv;
        this.primaryAccountId=primaryAccountId;
        this.customerId=customerId;
    }



}
