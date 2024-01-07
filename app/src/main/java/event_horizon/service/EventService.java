package event_horizon.service;

import event_horizon.model.Event;
import org.springframework.stereotype.Service;

@Service
public interface EventService {
    Event getById(Long id);
}
