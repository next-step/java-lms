package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private final SessionId id;
    private final SessionInfo info;
    private final Enrollment enrollment;

    public Session(Long id, String title, SessionStatus status, LocalDateTime startDate, LocalDateTime endDate,
                  SessionImage image, SessionType type, int maxEnrollment, int price) {
        this.id = new SessionId(id);
        this.info = new SessionInfo(title, status, image, startDate, endDate, type, price);
        this.enrollment = type == SessionType.FREE ? new FreeEnrollment() : new PaidEnrollment(maxEnrollment);
    }

    public void enroll(NsUser user, Payment payment) {
        if (!info.isRecruiting()) {
            throw new IllegalStateException("모집중인 강의만 수강 신청이 가능합니다.");
        }

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

    public boolean isRecruiting() {
        return info.isRecruiting();
    }

    public boolean isFull() {
        return enrollment.isFull();
    }

    public boolean canEnroll(NsUser user) {
        return isRecruiting() && !isFull() && !hasEnrolledUser(user);
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