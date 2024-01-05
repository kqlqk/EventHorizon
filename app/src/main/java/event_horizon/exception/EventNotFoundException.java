package event_horizon.exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String message) {
        super(message);
    }

    public EventNotFoundException(Long eventId) {
        super("Event with id=" + eventId + " not found");
    }
}
