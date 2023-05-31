package nextstep.courses.exception;

public class DuplicateStudentException extends RuntimeException{
    public DuplicateStudentException() {
    }

    public DuplicateStudentException(String message) {
        super(message);
    }
}
