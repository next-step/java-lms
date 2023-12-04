package nextstep.courses.exception;

public class InvalidPeriodException extends RuntimeException {
    public InvalidPeriodException() {
        super("시작일, 종료일은 필수값입니다.");
    }
}
