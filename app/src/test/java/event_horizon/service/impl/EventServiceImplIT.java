package event_horizon.service.impl;

import event_horizon.exception.EventNotFoundException;
import event_horizon.model.Event;
import event_horizon.repository.EventRepository;
import event_horizon.service.EventService;
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
    public void getEventById_ExistingId_ReturnsEvent() {
        Event event = eventRepository.findById(1L).get();

        Event foundEvent = eventService.getEventById(event.getId());

        assertNotNull(foundEvent);
        assertEquals(event.getId(), foundEvent.getId());
    }

    @Test
    public void getEventById_NonExistingId_ThrowsException() {
        assertThrows(EventNotFoundException.class, () -> {
            eventService.getEventById(999L);
        });
    }
}
