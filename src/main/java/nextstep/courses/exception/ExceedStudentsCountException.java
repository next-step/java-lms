package nextstep.courses.exception;

public class ExceedStudentsCountException extends RuntimeException {

    public ExceedStudentsCountException(String message) {
        super(message);
    }
}
