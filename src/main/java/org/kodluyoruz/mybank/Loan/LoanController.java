package org.kodluyoruz.mybank.Loan;

import org.hibernate.transform.CacheableResultTransformer;
import org.kodluyoruz.mybank.Card.CreditCard.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Loan")
public class LoanController {
    /*@Autowired
    LoanServiceImpl loanService;
    @GetMapping(params = {"cardnumber", "page", "size"})
    public List<Loan> listByAuthor(@RequestParam("cardnumber") long cardnumber, @Min(value = 0) @RequestParam("page") int page, @RequestParam("size") int size) {
        return loanService.list(cardnumber,PageRequest.of(page, size)).stream()
                .collect(Collectors.toList());
    }*/
}
