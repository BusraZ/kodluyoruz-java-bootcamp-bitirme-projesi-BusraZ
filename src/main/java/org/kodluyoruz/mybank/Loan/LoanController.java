package org.kodluyoruz.mybank.Loan;

import org.hibernate.transform.CacheableResultTransformer;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCard;
import org.kodluyoruz.mybank.Recipient.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Loan")
public class LoanController {
    @Autowired
    LoanServiceImpl loanService;
    /*
    @GetMapping(params = {"cardnumber", "page", "size"})
    public List<Loan> listByAuthor(@RequestParam("cardnumber") long cardnumber, @Min(value = 0) @RequestParam("page") int page, @RequestParam("size") int size) {
        return loanService.list(cardnumber,PageRequest.of(page, size)).stream()
                .collect(Collectors.toList());
    }*/

    @GetMapping(value = "/showExtre", params = {"cardnumber", "page", "size"})
    public List<Loan> CheckExtre(@RequestParam("cardnumber") long cardnumber, @Min(value = 0) @RequestParam("page") int page, @RequestParam("size") int size) {
        return  loanService.findBulLoanList(cardnumber,PageRequest.of(page, size)).stream()
                .collect(Collectors.toList());
    }
    @GetMapping("/checkLoan")
    public BigDecimal checkLoan(@RequestParam("cardnumber") long cardnumber){
        return loanService.check(cardnumber);
    }
    @PostMapping("/payLoanwithAccount")
    public void payLoan(   @RequestParam("iban") long iban,
                           @RequestParam("amount") double amount) throws Exception{
        loanService.payLoanwithAccount(iban,amount);
    }
    @PostMapping("/payLoanwithCard")
    public void payLoanCard(   @RequestParam("cardNumber") long cardnumber,
                           @RequestParam("amount") double amount) throws Exception{
        loanService.payLoanwithCard(cardnumber,amount);
    }

}
