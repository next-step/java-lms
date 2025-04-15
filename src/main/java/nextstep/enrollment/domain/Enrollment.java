package nextstep.enrollment.domain;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.Session;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Enrollment {
    private final NsUser student;
    private final Session session;
    private final LocalDateTime enrolledAt;
    private final Payment payment;

    public Enrollment(NsUser student, Session session, Payment payment) {
        validate(session, payment);
        this.student = student;
        this.session = session;
        this.enrolledAt = LocalDateTime.now();
        this.payment = payment;
    }

    private void validate(Session session, Payment payment) {
        validateSessionStatus(session);
        validatePaidCorrectly(session, payment);
    }

    private void validateSessionStatus(Session session) {
        if (!session.isRecruiting()) {
            throw new IllegalArgumentException("강의 상태가 모집중일 때만 수강 신청이 가능합니다.");
        }
    }

    private void validatePaidCorrectly(Session session, Payment payment) {
        if (!hasPaidCorrectly(session, payment)) {
            throw new IllegalArgumentException("결제 금액이 수강료와 일치하지 않습니다.");
        }
    }

    private boolean hasPaidCorrectly(Session session, Payment payment) {
        return Objects.equals(payment.amount(), session.price());
    }
}
