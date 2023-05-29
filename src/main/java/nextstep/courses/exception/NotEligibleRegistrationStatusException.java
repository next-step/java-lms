package nextstep.courses.exception;

public class NotEligibleRegistrationStatusException extends RuntimeException {

    public NotEligibleRegistrationStatusException() {
        super("강의가 모집상태가 아닙니다.");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
