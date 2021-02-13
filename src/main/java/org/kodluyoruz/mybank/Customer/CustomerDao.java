package org.kodluyoruz.mybank.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer,Long> {
Customer findByName(String name);
Customer findByPrimaryAccount_Iban(long iban);
Customer findBySavingAccount_Iban(long iban);
}
