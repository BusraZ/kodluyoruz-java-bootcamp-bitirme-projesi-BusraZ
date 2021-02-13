package org.kodluyoruz.mybank.Customer;

import lombok.*;
import org.kodluyoruz.mybank.Account.PrimaryAccount.PrimaryAccount;
import org.kodluyoruz.mybank.Account.SavingAccount.SavingAccount;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long Id;
    @NotBlank(message = "Name for the user is mandatory")
    private String name;
    @NotBlank(message = "Surname for the user is mandatory")
    private String surname;
    public Customer toCustomer() {
        return Customer.builder().Id(this.Id)
                .name(this.name)
                .surname(this.surname)
                .build();
    }
}

