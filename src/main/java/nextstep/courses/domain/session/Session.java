package nextstep.courses.domain.session;

import lombok.Getter;
import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.domain.session.info.SessionInfo;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

@Getter
public class Session {
    private final SessionId id;
    private final SessionInfo info;
    private final Enrollment enrollment;

    public Session(SessionId id, SessionInfo info, Enrollment enrollment) {
        this.id = id;
        this.info = info;
        this.enrollment = enrollment;
    }

    public void enroll(NsUser user, Payment payment) {
        if (info.isPaid()) {
            validatePaymentExists(payment);
            info.validatePayment(payment);
        }
        
        enrollment.enroll(user);
    }

    public boolean isPaid() {
        return info.isPaid();
    }

    private void validatePaymentExists(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("유료 강의는 결제가 필요합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return id.equals(session.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}