package org.kodluyoruz.mybank.Account;

import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccountDto;
import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccount;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;


public interface AccountService{

       PrimaryAccount createPrimaryAccount(Long id, PrimaryAccount primaryAccount, PrimaryAccount.Currency currency,PrimaryAccount.Card cardType);
        void deletePrimaryAccount(Long id)throws Exception;

        SavingAccount createSavingAccount(Long id, SavingAccount savingAccount, SavingAccount.Currency currency);
        void deleteSavingAccount(Long id) throws Exception;

        PrimaryAccount getPrimaryAccount(long iban);
        SavingAccount getSavingAccount(long iban);

        void deposit(String accountType, double amount, long iban );

        void withdraw(String accountType, double amount, long iban)throws Exception;
}
