package me.kqlqk.event_horizon.service.impl;

import me.kqlqk.event_horizon.exception.EventException;
import me.kqlqk.event_horizon.exception.EventNotFoundException;
import me.kqlqk.event_horizon.model.Customer;
import me.kqlqk.event_horizon.model.Event;
import me.kqlqk.event_horizon.model.Point;
import me.kqlqk.event_horizon.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;


    @Test
    public void getById_ExistingId_ReturnsEvent() {
        Long eventId = 1L;
        Event expectedEvent = new Event();
        expectedEvent.setId(eventId);
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(expectedEvent));

        Event resultEvent = eventService.getById(eventId);

        assertNotNull(resultEvent);
        assertEquals(expectedEvent, resultEvent);
    }

    @Test
    public void getById_NonExistingId_ThrowsException() {
        Long eventId = 2L;
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        assertThrows(EventNotFoundException.class, () -> eventService.getById(eventId));
    }

    @Test
    public void add_SavesEvent() {
        Event event = new Event("TestEvent", "Test description", "NY",
                new Point(32.123, 43.3212), LocalDateTime.now(), new Customer());

        eventService.add(event);

        verify(eventRepository).save(event);
    }

    @Test
    public void add_ThrowsEventException() {
        Event event = new Event(null, "Test description", "NY",
                new Point(32.123, 43.3212), LocalDateTime.now(), new Customer());
        assertThrows(EventException.class, () -> eventService.add(event));

        event.setName("Test name");
        event.setDescription(null);
        assertThrows(EventException.class, () -> eventService.add(event));

        event.setDescription("Test description");
        event.setPlace(null);
        assertThrows(EventException.class, () -> eventService.add(event));

        event.setPlace("NY");
        event.setPoint(null);
        assertThrows(EventException.class, () -> eventService.add(event));

        event.setPoint(new Point(32.123, 43.3212));
        event.setTime(null);
        assertThrows(EventException.class, () -> eventService.add(event));

        event.setTime(LocalDateTime.now());
        event.setCreator(null);
        assertThrows(EventException.class, () -> eventService.add(event));

        event.setCreator(new Customer());
        event.setName("   ");
        assertThrows(EventException.class, () -> eventService.add(event));

        event.setName("Test Name");
        event.setDescription("   ");
        assertThrows(EventException.class, () -> eventService.add(event));

        event.setDescription("Test description");
        event.setPlace("   ");
        assertThrows(EventException.class, () -> eventService.add(event));
    }
}