package nextstep.courses.domain;

public class MaxStudentsExceedException extends RuntimeException {

    public MaxStudentsExceedException(String message) {
        super(message);
    }
}
