package nextstep.courses.exception;

public class NotOpenSessionException extends RuntimeException {

    public NotOpenSessionException(String message) {
        super(message);
    }

    public NotOpenSessionException() {
        this("강의상태가 모집중이 아닙니다.");
    }
}
