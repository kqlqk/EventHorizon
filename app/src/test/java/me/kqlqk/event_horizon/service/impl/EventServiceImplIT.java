package me.kqlqk.event_horizon.service.impl;

import me.kqlqk.event_horizon.exception.EventExistsException;
import me.kqlqk.event_horizon.exception.EventNotFoundException;
import me.kqlqk.event_horizon.model.Event;
import me.kqlqk.event_horizon.repository.EventRepository;
import me.kqlqk.event_horizon.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init_db.sql")
public class EventServiceImplIT {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void getById_ExistingId_ReturnsEvent() {
        Event event = eventRepository.findById(1L).get();

        Event foundEvent = eventService.getById(event.getId());

        assertNotNull(foundEvent);
        assertEquals(event.getId(), foundEvent.getId());
    }

    @Test
    public void getById_NonExistingId_ThrowsException() {
        assertThrows(EventNotFoundException.class, () -> {
            eventService.getById(999L);
        });
    }

    @Test
    public void add_SavesEvent() {
        Event newEvent = eventService.getById(1L);
        newEvent.setId(0L);

        eventService.add(newEvent);

        Event savedEvent = eventRepository.findById(newEvent.getId()).orElse(null);
        assertNotNull(savedEvent);
    }

    @Test
    public void add_ThrowsException() {
        assertThrows(EventExistsException.class, () -> {
            eventService.add(eventService.getById(1L));
        });
    }

    @Test
    public void update_UpdatesEvent() {
        Event eventToUpdate = eventService.getById(1L);
        eventToUpdate.setName("New event name");

        eventService.update(eventToUpdate);

        Event updatedEvent = eventRepository.findById(1L).get();
        assertNotNull(updatedEvent);
        assertThat(updatedEvent.getName()).isEqualTo(eventToUpdate.getName());
    }

    @Test
    public void update_ThrowsException() {
        assertThrows(EventNotFoundException.class, () -> {
            Event e = eventService.getById(1L);
            e.setId(999L);
            eventService.update(e);
        });
    }
}
