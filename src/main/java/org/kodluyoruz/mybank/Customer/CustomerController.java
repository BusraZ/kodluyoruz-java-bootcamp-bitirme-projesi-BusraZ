package org.kodluyoruz.mybank.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
   private final CustomerServiceImpl customerService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto create(@Valid @RequestBody CustomerDto customerDto) {


        return customerService.create(customerDto.toCustomer()).toCustomerDto();
    }
    @GetMapping("/{id}")
    public CustomerDto get(@PathVariable("id") Long id) {
        return customerService.get(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : " + id)).toCustomerDto();
    }
    @PutMapping("/{id}")
    public CustomerDto update(@RequestBody CustomerDto customerDto, @PathVariable Long id) {
        return customerService.update(id,customerDto.toCustomer()).toCustomerDto();
    }
    @DeleteMapping("/{id}")
   public void delete(@PathVariable(value = "id") Long id) {

        customerService.deleteCustomerById(id);
    }

}