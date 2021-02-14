package org.kodluyoruz.mybank.Transaction.SavingTransaction;

import org.kodluyoruz.mybank.Transaction.SavingTransaction.SavingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavingTransactionDao extends JpaRepository<SavingTransaction, Long> {

    List<SavingTransaction> findAll();
}
