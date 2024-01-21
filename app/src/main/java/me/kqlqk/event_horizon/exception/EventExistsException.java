package me.kqlqk.event_horizon.exception;

public class EventExistsException extends RuntimeException {
    public EventExistsException(String message) {
        super(message);
    }

    public EventExistsException(Long eventId) {
        super("Event with id=" + eventId + " already exists");
    }
}
