package nextstep.courses.domain.session;

import lombok.Getter;
import nextstep.courses.domain.session.enrollment.Enrollments;
import nextstep.courses.domain.session.info.SessionInfo;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

@Getter
public class Session {
    private final SessionId id;
    private final SessionInfo info;
    private final Enrollments enrollments;

    public Session(SessionId id, SessionInfo info, Enrollments enrollments) {
        this.id = id;
        this.info = info;
        this.enrollments = enrollments;
    }

    public void enroll(NsUser user, Payment payment) {
        if (info.isPaid()) {
            validatePaymentExists(payment);
            info.validatePayment(payment);
        }
        
        enrollments.enroll(user);
    }

    public void approve(NsUser user) {
        enrollments.approve(user);
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