package nextstep.courses;

public class InvalidTimeRangeException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidTimeRangeException(String message) {
        super(message);
    }
}
