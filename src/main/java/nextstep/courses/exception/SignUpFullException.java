package nextstep.courses.exception;

public class SignUpFullException extends RuntimeException{
    public SignUpFullException() {
    }

    public SignUpFullException(String message) {
        super(message);
    }
}
