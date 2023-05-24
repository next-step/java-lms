package nextstep.courses.exception;

public class NotReadySessionException extends RuntimeException {

    public NotReadySessionException(String message) {
        super(message);
    }

    public NotReadySessionException() {
        this("강의상태가 모집중이 아닙니다.");
    }
}
