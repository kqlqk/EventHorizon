package event_horizon.service.impl;

import event_horizon.exception.CustomerNotFoundException;
import event_horizon.exception.EventNotFoundException;
import event_horizon.model.Customer;
import event_horizon.model.Event;
import event_horizon.repository.CustomerRepository;
import event_horizon.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init_db.sql")
public class CustomerServiceImplIT {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void getCustomerById_ExistingId_ReturnsCustomer() {
        Long customerId = 1L;

        Customer customer = customerRepository.findById(customerId).get();

        Customer foundCustomer = customerService.getById(customerId);

        assertNotNull(foundCustomer);
        assertEquals(customer.getId(), foundCustomer.getId());
    }

    @Test
    public void getCustomerById_NonExistingId_ThrowsException() {
        assertThrows(CustomerNotFoundException.class, () -> {
            customerService.getById(999L);
        });
    }
}
