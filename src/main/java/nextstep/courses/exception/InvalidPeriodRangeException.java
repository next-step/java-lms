package nextstep.courses.exception;

public class InvalidPeriodRangeException extends RuntimeException {
    public InvalidPeriodRangeException() {
        super("강의 종료일은 시작일보다 미래여야 합니다.");
    }
}
