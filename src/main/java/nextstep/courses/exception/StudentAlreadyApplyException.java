package nextstep.courses.exception;

public class StudentAlreadyApplyException extends RuntimeException {

    public StudentAlreadyApplyException(String message) {
        super(message);
    }
}
