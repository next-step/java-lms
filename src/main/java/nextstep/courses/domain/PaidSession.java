package nextstep.courses.domain;

import nextstep.courses.CannotSignUpException;
import nextstep.courses.enumeration.SessionStatus;
import nextstep.payments.domain.Payment;

public class PaidSession extends Session {
    private int maxStudentCount;
    private Long sessionFee;

    public static PaidSession feeOf(String title, int maxStudentCount, Long sessionFee) {
        return new PaidSession(1L, title, null
                , SessionStatus.RECRUITING, maxStudentCount, sessionFee);
    }

    public PaidSession(Long sessionId, String title, Course course, SessionStatus sessionStatus
            , int maxStudentCount, Long sessionFee) {
        super(sessionId, title, course, sessionStatus);
        this.maxStudentCount = maxStudentCount;
        this.sessionFee = sessionFee;
    }

    @Override
    public void signUp(Payment payment) throws CannotSignUpException {
        validateAvailableSignUp();
        validateSessionFeeMatchingPayment(payment);
        super.signUp(payment);
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

    public Long getSessionFee() {
        return sessionFee;
    }

    @Override
    public String toString() {
        return "PaidSession{" +
                "maxStudentCount=" + maxStudentCount +
                ", sessionFee=" + sessionFee +
                '}';
    }
}
