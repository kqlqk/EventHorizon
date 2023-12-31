package me.kqlqk.event_horizon.service.impl;

import me.kqlqk.event_horizon.exception.EventNotFoundException;
import me.kqlqk.event_horizon.model.Event;
import me.kqlqk.event_horizon.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
}