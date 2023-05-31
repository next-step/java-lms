package nextstep.courses.exception;

public class NoSuchEnrollmentException extends RuntimeException {

    public NoSuchEnrollmentException(String message) {
        super(message);
    }

    public NoSuchEnrollmentException() {
        this("해당 수강신청은 존재하지 않습니다.");
    }
}
