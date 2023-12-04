package nextstep.courses.exception;

public class NotOpenSessionException extends RuntimeException {
    public NotOpenSessionException() {
        super("모집 중인 강의가 아닙니다.");
    }
}
