package nextstep.courses.exception;

public class OutOfRegistrationPeriod extends IllegalArgumentException {
    public OutOfRegistrationPeriod() {
        super("수강신청 기간이 아닙니다");
    }
}
