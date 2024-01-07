package me.kqlqk.event_horizon.service;

import me.kqlqk.event_horizon.model.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Customer getById(Long id);
}
