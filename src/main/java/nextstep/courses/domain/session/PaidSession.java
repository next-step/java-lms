package nextstep.courses.domain.session;

import nextstep.courses.CannotSignUpException;
import nextstep.courses.domain.Course;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class PaidSession extends Session {
    private int maxStudentCount;
    private Long sessionFee;

    public static PaidSession feeOf(String title, int maxStudentCount, Long sessionFee) {
        return new PaidSession(0L, title, null, maxStudentCount, sessionFee, LocalDate.now(), LocalDate.now());
    }

    public PaidSession(Long sessionId, String title, Course course, int maxStudentCount, Long sessionFee, LocalDate startDate, LocalDate endDate) {
        super(sessionId, title, course, SessionType.PAID, new SessionPlan(SessionStatus.fromDate(startDate, endDate), startDate, endDate));
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
