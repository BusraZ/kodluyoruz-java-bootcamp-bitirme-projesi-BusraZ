package org.kodluyoruz.mybank.Loan;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan,Long> {
  //  Page<Loan> findLoanList(long cardnumber, Pageable pageable);
}
