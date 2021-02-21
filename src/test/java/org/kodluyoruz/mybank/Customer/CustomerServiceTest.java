package org.kodluyoruz.mybank.Customer;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CustomerServiceTest {


    @Test
     public void createTest() {
        Customer customer = new Customer();
        CustomerService customerService= mock(CustomerService.class);
        customerService.create( customer);

        verify(customerService).create(customer);
    }

    @Test
    public void updateTest() {
        Customer customer = new Customer();
        CustomerService customerService= mock(CustomerService.class);
        customerService.update(customer.getId(),customer);
        verify(customerService).update(customer.getId(),customer);
    }

    @Test
     public void deleteCustomerById() {
        Customer customer = new Customer();
        CustomerService customerService= mock(CustomerService.class);
        customerService.deleteCustomerById(customer.getId());
        verify(customerService).deleteCustomerById(customer.getId());
    }

    @Test
     public   void get() {
        Customer customer = new Customer();
        CustomerService customerService= mock(CustomerService.class);
        customerService.get(customer.getId());
        verify(customerService).get(customer.getId());
    }
}