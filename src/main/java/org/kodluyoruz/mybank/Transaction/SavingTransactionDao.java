package org.kodluyoruz.mybank.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SavingTransactionDao extends JpaRepository<SavingTransaction, Long> {

    List<SavingTransaction> findAll();
}
