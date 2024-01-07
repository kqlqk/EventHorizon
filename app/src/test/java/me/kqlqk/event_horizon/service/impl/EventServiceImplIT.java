package me.kqlqk.event_horizon.service.impl;

import me.kqlqk.event_horizon.exception.EventNotFoundException;
import me.kqlqk.event_horizon.model.Event;
import me.kqlqk.event_horizon.repository.EventRepository;
import me.kqlqk.event_horizon.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

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
}
