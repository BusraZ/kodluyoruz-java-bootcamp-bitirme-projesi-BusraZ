package org.kodluyoruz.mybank.Loan;

import org.kodluyoruz.mybank.Recipient.Recipient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Long> {
  //  Page<Loan> findLoanList(long cardnumber, Pageable pageable);
  List<Loan> findAll();
}
