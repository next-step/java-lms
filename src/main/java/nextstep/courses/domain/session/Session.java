package nextstep.courses.domain.session;

import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.domain.session.enrollment.FreeEnrollment;
import nextstep.courses.domain.session.enrollment.PaidEnrollment;
import nextstep.courses.domain.session.info.SessionInfo;
import nextstep.courses.domain.session.info.basic.SessionThumbnail;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private final SessionId id;
    private final SessionInfo info;
    private final Enrollment enrollment;

    public Session(Long id, String title, LocalDateTime startDate, LocalDateTime endDate,
                   SessionThumbnail thumbnail, SessionType type, int maxEnrollment, int price) {
        this.id = new SessionId(id);
        this.info = new SessionInfo(title, thumbnail, startDate, endDate, type, price);
        this.enrollment = type.isPaid() ? new PaidEnrollment(maxEnrollment) : new FreeEnrollment();
    }

    public void enroll(NsUser user, Payment payment) {
        if (info.isPaid()) {
            validatePaymentExists(payment);
            info.validatePayment(payment);
        }
        
        enrollment.enroll(user);
    }

    public boolean hasEnrolledUser(NsUser user) {
        return enrollment.hasEnrolledUser(user);
    }

    public boolean isPaid() {
        return info.isPaid();
    }

    public boolean isFull() {
        return enrollment.isFull();
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