package org.kodluyoruz.mybank.Account.SavingAccount;

import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;


public interface SavingAccountDao  extends CrudRepository<SavingAccount,Long> {

    SavingAccount findByAccountNumber (int accountNumber);
    SavingAccount findByIban(long iban);
}
