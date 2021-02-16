package org.kodluyoruz.mybank.Recipient;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecipientService {
    List<Recipient> findRecipientList(Long id, Pageable pageable);

    Recipient saveRecipient(Recipient recipient);

    Recipient findRecipientByName(String recipientName);

    void deleteRecipientByName(String recipientName);
}
