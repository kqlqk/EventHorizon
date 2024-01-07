package event_horizon.service;

import event_horizon.model.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Customer getById(Long id);
}
