package org.kodluyoruz.mybank.Account.PrimaryAccount;

import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public  interface PrimaryAccountDao extends JpaRepository<PrimaryAccount,Long> {

    PrimaryAccount findByAccountNumber (int accountNumber);
    PrimaryAccount findByIban(long iban);
}
