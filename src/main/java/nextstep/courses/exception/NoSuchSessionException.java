package nextstep.courses.exception;

public class NoSuchSessionException extends RuntimeException {

    public NoSuchSessionException(String message) {
        super(message);
    }

    public NoSuchSessionException() {
        this("해당 강의는 존재하지 않습니다.");
    }
}
