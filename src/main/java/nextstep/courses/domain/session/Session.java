package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.strategy.PaymentStrategy;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class Session {
    private final SessionImage image;
    private final SessionDate date;
    private final SessionState state;
    private final Enrollment enrollment;
    private final PaymentStrategy paymentStrategy;

    public Session(SessionImage image, SessionDate date, SessionState state, Enrollment enrollment, PaymentStrategy paymentStrategy) {
        this.image = image;
        this.date = date;
        this.state = state;
        this.enrollment = enrollment;
        this.paymentStrategy = paymentStrategy;
    }

    public void applySession(NsUser user, LocalDate enrollDate, Payment payment) {
        if (!canApply(enrollDate, payment)) {
            throw new CannotEnrollException("등록 불가능한 상태입니다.");
        }
        this.enrollment.enroll(user);
    }

    private boolean canApply(LocalDate enrollDate, Payment payment) {
        if (!state.canRecruit()) {
            return false;
        }

        if (!date.isBefore(enrollDate)) {
            return false;
        }

        return paymentStrategy.payable(payment);
    }
}
