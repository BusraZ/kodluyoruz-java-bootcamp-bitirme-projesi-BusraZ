package org.kodluyoruz.mybank.Loan;

import org.kodluyoruz.mybank.Card.CreditCard.CreditCard;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.CacheRequest;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService{
  /*  @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    LoanRepository loanRepository;

    public Page<Loan> list(long cardnumber, Pageable pageable) {
      return loanRepository.findLoanList(cardnumber,pageable);

    }*/
}
