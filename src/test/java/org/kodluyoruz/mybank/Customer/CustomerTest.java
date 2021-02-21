package org.kodluyoruz.mybank.Customer;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CustomerTest {

        @Test
        public void test_customer_default_constructor() {
            Customer testClass = new Customer();

            testClass.setName("Shan");
            assertEquals("Shan", testClass.getName());

            testClass.setSurname("Mary");
            assertEquals("Mary", testClass.getSurname());

        }
}
