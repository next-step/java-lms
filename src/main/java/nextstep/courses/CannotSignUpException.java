package nextstep.courses;

public class CannotSignUpException extends RuntimeException{
    public CannotSignUpException(String message) {
        super(message);
    }
}