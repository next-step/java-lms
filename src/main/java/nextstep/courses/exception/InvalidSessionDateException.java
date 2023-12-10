package nextstep.courses.exception;

public class InvalidSessionDateException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidSessionDateException(String message) {
        super(message);
    }
}
