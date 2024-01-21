package me.kqlqk.event_horizon.service;

import me.kqlqk.event_horizon.model.Event;
import org.springframework.stereotype.Service;

@Service
public interface EventService {
    Event getById(Long id);

    void add(Event e);

    void update(Event e);

    void remove(Long eventId);
}
