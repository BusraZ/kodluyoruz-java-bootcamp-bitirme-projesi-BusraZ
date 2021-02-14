package org.kodluyoruz.mybank.Account;

import lombok.RequiredArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccountDao;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccountDto;
import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccount;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountServiceImpl accountServiceImpl;

   /* private CustomerService customerService;

private PrimaryAccountDao primaryAccountDao;
private SavingAccountDao savingAccountDao;
    private AccountService accountService;
    private CustomerDao customerDao;  */

 //   @Autowired
  //  private TransactionService transactionService;

 /*   @RequestMapping(value = "/primaryaccount", method = RequestMethod.GET)
    public String deposit(Model model) {
        model.addAttribute("accountType", "");
        model.addAttribute("amount", "");

        return "deposit";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String depositPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal) {
        accountService.deposit(accountType, Double.parseDouble(amount), principal);

        return "redirect:/userFront";

    } */

   @PostMapping("/{id}/new/primaryaccount")
    @ResponseStatus(HttpStatus.CREATED)
    public PrimaryAccount  create(@PathVariable("id")Long id, @Valid @RequestBody PrimaryAccount primaryAccount , @RequestParam("currency") PrimaryAccount.Currency currency){

            return accountServiceImpl.createPrimaryAccount(id,primaryAccount,currency) ;
     }

     @PostMapping("/{id}/new/savingaccount")
     @ResponseStatus(HttpStatus.CREATED)
     public SavingAccount create(@PathVariable("id")Long id, @Valid @RequestBody SavingAccount savingAccount, @RequestParam("currency") SavingAccount.Currency currency){
       return accountServiceImpl.createSavingAccount(id,savingAccount,currency);
     }
   @PostMapping("/deposit")
    public void depositPOST(
           @RequestParam("iban") long iban,
            @RequestParam("amount") double amount,
            @RequestParam("accountType") String accountType) {
        accountServiceImpl.deposit(accountType, amount, iban);

    }

    @PostMapping("/{id}/withdraw")
    public void withdrawPOST(
            @RequestParam("iban") long iban,
            @RequestParam("amount") String amount,
            @RequestParam("accountType") String accountType) {
        accountServiceImpl.withdraw(accountType, Double.parseDouble(amount), iban);

    }
}
