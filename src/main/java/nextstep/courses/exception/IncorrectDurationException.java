package nextstep.courses.exception;

public class IncorrectDurationException extends RuntimeException {

    public IncorrectDurationException() {
        super("시작일시가 종료일시보다 뒤에있습니다.");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
