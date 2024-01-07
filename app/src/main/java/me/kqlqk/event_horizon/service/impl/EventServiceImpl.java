package me.kqlqk.event_horizon.service.impl;

import me.kqlqk.event_horizon.exception.EventNotFoundException;
import me.kqlqk.event_horizon.model.Event;
import me.kqlqk.event_horizon.repository.EventRepository;
import me.kqlqk.event_horizon.service.EventService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event getById(@NonNull Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
    }
}
