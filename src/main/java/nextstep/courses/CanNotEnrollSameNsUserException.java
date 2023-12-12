package nextstep.courses;

public class CanNotEnrollSameNsUserException extends RuntimeException {

    public CanNotEnrollSameNsUserException(String message) {
        super(message);
    }
}
