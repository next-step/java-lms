package nextstep.courses;

public class NotIncludePeriodException extends RuntimeException{

    public static final String MESSAGE = "수강신청 가능한 기간이 아닙니다.";

    public NotIncludePeriodException() {
    }

    public NotIncludePeriodException(String message) {
        super(message);
    }
}
