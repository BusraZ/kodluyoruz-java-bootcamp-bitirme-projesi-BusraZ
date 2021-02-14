package org.kodluyoruz.mybank.Transaction.PrimaryTransaction;

import org.kodluyoruz.mybank.Transaction.PrimaryTransaction.PrimaryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrimaryTransactionDao extends JpaRepository<PrimaryTransaction, Long> {

        List<PrimaryTransaction> findAll();
}
