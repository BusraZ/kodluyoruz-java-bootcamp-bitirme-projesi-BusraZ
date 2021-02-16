package org.kodluyoruz.mybank.Loan;

import org.kodluyoruz.mybank.Recipient.Recipient;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface LoanService {
    List<Loan> findBulLoanList(long cardnumber, Pageable pageable);
    BigDecimal check(long cardnumber);
    void payLoanwithAccount(long cardnumber,double amount)throws Exception;
    void payLoanwithCard(long iban,double amount)throws Exception;
}
