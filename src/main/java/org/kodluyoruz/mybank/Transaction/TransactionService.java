package org.kodluyoruz.mybank.Transaction;

import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Recipient.Recipient;
import org.kodluyoruz.mybank.Transaction.PrimaryTransaction.PrimaryTransaction;
import org.kodluyoruz.mybank.Transaction.SavingTransaction.SavingTransaction;

import java.util.List;

public interface TransactionService {
    List<PrimaryTransaction> findPrimaryTransactionList(Long id);

    List<SavingTransaction> findSavingTransactionList(Long id);

    void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);

    void saveSavingDepositTransaction(SavingTransaction savingTransaction);

    void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction);

    void saveSavingWithdrawTransaction(SavingTransaction savingTransaction);

    void betweenAccountsTransfer (long transferFrom, long transferTo, String amount,String TransferType) throws Exception ;

   List<Recipient> findRecipientList(Long id);

    Recipient saveRecipient(Recipient recipient);

    Recipient findRecipientByName(String recipientName);

    void deleteRecipientByName(String recipientName);

    void toSomeoneElseTransfer(Recipient recipient, String accountType, String amount, PrimaryAccount primaryAccount) throws Exception;
}
