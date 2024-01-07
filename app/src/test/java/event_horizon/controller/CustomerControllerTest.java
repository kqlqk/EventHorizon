package event_horizon.controller;

import event_horizon.exception.CustomerNotFoundException;
import event_horizon.exception.EventNotFoundException;
import event_horizon.model.Customer;
import event_horizon.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @Mock
    private CustomerService customerService;

    @Mock
    private Model model;

    @InjectMocks
    private CustomerController customerController;


    @Test
    public void getCustomerPage_ReturnsCustomerPage() {
        Long customerId = 1L;
        Customer mockCustomer = new Customer();
        when(customerService.getById(customerId)).thenReturn(mockCustomer);

        String viewName = customerController.getCustomerPage(customerId, model);

        verify(customerService).getById(customerId);
        verify(model).addAttribute("customer", mockCustomer);
        assertEquals("customer/CustomerPage", viewName);
    }

    @Test
    public void getCustomerPage_ThrowsException() {
        Long customerId = 1L;
        when(customerService.getById(customerId)).thenThrow(new CustomerNotFoundException(customerId));

        assertThrows(CustomerNotFoundException.class, () -> customerController.getCustomerPage(customerId, model));
    }
}