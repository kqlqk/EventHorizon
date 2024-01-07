package me.kqlqk.event_horizon.service.impl;

import me.kqlqk.event_horizon.exception.CustomerNotFoundException;
import me.kqlqk.event_horizon.model.Customer;
import me.kqlqk.event_horizon.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void getCustomerById_ExistingId_ReturnsCustomer() {
        Long customerId = 1L;
        Customer expectedCustomer = new Customer();
        expectedCustomer.setId(customerId);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(expectedCustomer));

        Customer resultCustomer = customerService.getById(customerId);

        assertNotNull(resultCustomer);
        assertEquals(expectedCustomer, resultCustomer);
    }

    @Test
    public void getCustomerById_NonExistingId_ThrowsException() {
        Long customerId = 2L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getById(customerId));
    }
}