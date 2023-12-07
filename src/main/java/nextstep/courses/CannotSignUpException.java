package nextstep.courses;

public class CannotSignUpException extends Exception{
    public CannotSignUpException(String message) {
        super(message);
    }
}