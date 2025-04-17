package nextstep.sessions.exception;

public class AttendeeException extends RuntimeException {

    public AttendeeException() {
        super("Invalid attendee");
    }

    public AttendeeException(String message) {
        super(message);
    }

    public AttendeeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AttendeeException(Throwable cause) {
        super(cause);
    }

}
