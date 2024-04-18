package nextstep.session;

public class StudentAlreadyEnrolledException extends RuntimeException {

    public StudentAlreadyEnrolledException(String message) {
        super(message);
    }
}
