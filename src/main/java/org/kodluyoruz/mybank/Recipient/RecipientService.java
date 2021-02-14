package org.kodluyoruz.mybank.Recipient;

import java.util.List;

public interface RecipientService {
    List<Recipient> findRecipientList(Long id);

    Recipient saveRecipient(Recipient recipient);

    Recipient findRecipientByName(String recipientName);

    void deleteRecipientByName(String recipientName);
}
