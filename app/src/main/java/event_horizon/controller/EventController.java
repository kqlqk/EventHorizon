package event_horizon.controller;


import event_horizon.model.Event;
import event_horizon.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("/{id}")
    public String getEventPage(@PathVariable Long id, Model model) {
        Event event = eventService.getById(id);

        model.addAttribute("event", event);

        return "event/EventPage";
    }
}
