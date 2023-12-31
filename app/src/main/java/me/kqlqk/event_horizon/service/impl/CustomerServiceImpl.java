package me.kqlqk.event_horizon.service.impl;

import me.kqlqk.event_horizon.exception.CustomerNotFoundException;
import me.kqlqk.event_horizon.model.Customer;
import me.kqlqk.event_horizon.repository.CustomerRepository;
import me.kqlqk.event_horizon.service.CustomerService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getById(@NonNull Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }
}
