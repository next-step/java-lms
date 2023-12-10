package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;

public class Enrollment {

    private final Session session;
    private int enrollmentCount;

    private Enrollment(Payment payment, Session session) {
        validateSessionStatus(session);
        validateAmount(payment, session);
        validateEnrollmentCount(session);
        this.session = session;
        ++enrollmentCount;
    }

    public Enrollment(Session session) {
        this(null, session);
    }

    public static Enrollment enrollFreeSession(Session session) {
        return new Enrollment(null, session);
    }

    public static Enrollment enrollPaidSession(Payment payment, Session session) {
        return new Enrollment(payment, session);
    }

    private void validateSessionStatus(Session session) {
        if (!session.isOpened()) {
            throw new IllegalArgumentException("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.");
        }
    }

    private void validateAmount(Payment payment, Session session) {
        if (!session.isEnrollmentAmountValid(payment)) {
            throw new IllegalArgumentException("결제한 금액과 수강료가 일치하지 않습니다.");
        }
    }

    private void validateEnrollmentCount(Session session) {
        if (session.isExceededMaxEnrollment(enrollmentCount + 1)) {
            throw new IllegalArgumentException("강의 최대 수강 인원을 초과했습니다.");
        }
    }
}
