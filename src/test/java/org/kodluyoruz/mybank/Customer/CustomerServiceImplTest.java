package org.kodluyoruz.mybank.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {


    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerRepository customerRepository;
   @Test
  public void testCreate() {
        Customer customer= new Customer();
        customer.setName("Test-name");
        customer.setSurname("Test-surname");
        Customer mock = mock(Customer.class);

        when(mock.getId()).thenReturn(1L);
        when(customerRepository.save(any(Customer.class))).thenReturn(mock);
       Customer  result = customerService.create(customer);
  //     assertNotNull(customer.getId());

       assertEquals(result.getName(),customer.getName());

     //   assertEquals(result.getId(),1L);
    }


}