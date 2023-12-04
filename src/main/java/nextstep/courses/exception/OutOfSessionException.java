package nextstep.courses.exception;

public class OutOfSessionException extends RuntimeException {
    public OutOfSessionException() {
        super("현재 날짜가 강의 기간에 속하지 않아 상태 변경이 불가능합니다.");
    }
}
