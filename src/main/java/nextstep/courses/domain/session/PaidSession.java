package nextstep.courses.domain.session;

import nextstep.courses.CannotSignUpException;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.SystemTimeStamp;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PaidSession extends Session {
    private int maxStudentCount;
    private Long sessionFee;

    public static PaidSession feeOf(long id, String title, EnrollmentStatus enrollmentStatus,
                                    LocalDateTime startDate, LocalDateTime endDate, int maxStudentCount, Long sessionFee) {
        return new PaidSession(id, title, null, enrollmentStatus, LocalDate.now(), LocalDate.now(), maxStudentCount, sessionFee);
    }

    public PaidSession(Long sessionId, String title, Course course, EnrollmentStatus enrollmentStatus
            , LocalDate startDate, LocalDate endDate, int maxStudentCount, Long sessionFee) {
        super(sessionId, title, SessionType.PAID
                , new SessionPlan(enrollmentStatus, startDate, endDate)
                , new SystemTimeStamp(LocalDateTime.now(), null));
        this.maxStudentCount = maxStudentCount;
        this.sessionFee = sessionFee;
    }

    @Override
    public void signUp(NsUser nsUser, Payment payment) throws CannotSignUpException {
        validateAvailableSignUp();
        validateSessionFeeMatchingPayment(payment);
        super.signUp(nsUser, payment);
    }

    private void validateSessionFeeMatchingPayment(Payment payment) throws CannotSignUpException {
        if (payment.isNotSamePrice(sessionFee)) {
            throw new CannotSignUpException("결제금액과 수강료가 일치하지 않습니다.");
        }
    }

    private void validateAvailableSignUp() throws CannotSignUpException {
        if (maxStudentCount == super.getStudentCount()) {
            throw new CannotSignUpException("최대 수강 인원을 초과했습니다.");
        }
    }

    @Override
    public String toString() {
        return "PaidSession{" +
                "maxStudentCount=" + maxStudentCount +
                ", sessionFee=" + sessionFee +
                '}';
    }
}
