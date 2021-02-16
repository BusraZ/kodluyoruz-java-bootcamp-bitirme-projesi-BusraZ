package org.kodluyoruz.mybank.Card.BankCard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Customer.Customer;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BankCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long number;
    private LocalDate expDate;
    private int cvv;
    private double amount;
    @OneToOne
    @JoinColumn(name = "accountNo",referencedColumnName = "id")
    @JsonIgnore
    private PrimaryAccount accountNo;
    @OneToOne
    @JoinColumn(name = "customerId",referencedColumnName = "Id")
    private Customer customerId;

    public BankCard(long cardNumber,LocalDate expDate,int cvv,PrimaryAccount accountNo,Customer customerId,double amount){
        this.number=cardNumber;
        this.expDate=expDate;
        this.cvv=cvv;
        this.accountNo=accountNo;
        this.amount=amount;
        this.customerId=customerId;
    }



}
