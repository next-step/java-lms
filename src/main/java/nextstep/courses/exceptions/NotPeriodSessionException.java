package nextstep.courses.exceptions;

public class NotPeriodSessionException extends RuntimeException {

    public NotPeriodSessionException(String message) {
        super(message);
    }

    public NotPeriodSessionException() {
        this("수강신청은 모집 중에만 가능합니다.");
    }
}
