package nextstep.courses;

public class CanNotEnrollSameStudentsException extends RuntimeException {

    public CanNotEnrollSameStudentsException(String message) {
        super(message);
    }
}
