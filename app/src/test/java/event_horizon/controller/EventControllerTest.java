package event_horizon.controller;

import event_horizon.exception.EventNotFoundException;
import event_horizon.model.Event;
import event_horizon.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventControllerTest {
    @Mock
    private EventService eventService;

    @Mock
    private Model model;

    @InjectMocks
    private EventController eventController;


    @Test
    public void getEventPage_ReturnsEventPage() {
        Long eventId = 1L;
        Event mockEvent = new Event();
        when(eventService.getById(eventId)).thenReturn(mockEvent);

        String viewName = eventController.getEventPage(eventId, model);

        verify(eventService).getById(eventId);
        verify(model).addAttribute("event", mockEvent);
        assertEquals("event/EventPage", viewName);
    }

    @Test
    public void getEventPage_ThrowsException() {
        Long eventId = 1L;
        when(eventService.getById(eventId)).thenThrow(new EventNotFoundException(eventId));

        assertThrows(EventNotFoundException.class, () -> eventController.getEventPage(eventId, model));
    }
}