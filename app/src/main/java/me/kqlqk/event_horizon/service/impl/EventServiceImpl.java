package me.kqlqk.event_horizon.service.impl;

import me.kqlqk.event_horizon.exception.EventException;
import me.kqlqk.event_horizon.exception.EventExistsException;
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

    @Override
    public void add(@NonNull Event e) {
        if (eventRepository.existsById(e.getId())) {
            throw new EventExistsException(e.getId());
        }

        if (isNull(e.getName()) || isNull(e.getDescription()) || isNull(e.getPlace()) || isNull(e.getPoint()) ||
                isNull(e.getTime()) || isNull(e.getCreator())) {
            throw new EventException("All fields should not be nullable");
        }

        eventRepository.save(e);
    }

    @Override
    public void update(@NonNull Event toUpdate) {
        if (!eventRepository.existsById(toUpdate.getId())) {
            throw new EventNotFoundException(toUpdate.getId());
        }

        if (isNull(toUpdate.getName()) || isNull(toUpdate.getDescription()) || isNull(toUpdate.getPlace()) ||
                isNull(toUpdate.getTime()) || isNull(toUpdate.getPoint()) || isNull(toUpdate.getCreator())) {
            throw new EventException("All fields should not be nullable");
        }

        eventRepository.save(toUpdate);
    }

    @Override
    public void remove(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    private boolean isNull(Object toCheck) {
        if (toCheck instanceof String) {
            return ((String) toCheck).isBlank();
        }

        return toCheck == null;
    }

}
