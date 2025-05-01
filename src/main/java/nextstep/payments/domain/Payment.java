package nextstep.payments.domain;

import lombok.Getter;
import nextstep.common.domian.BaseDomain;
import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

import static nextstep.payments.domain.PaymentStatus.PENDING;

@Getter
public class Payment extends BaseDomain {

    private Session session;

    private NsUser user;

    private Long amount;

    private PaymentStatus status;

    public Payment() {
    }

    public Payment(Session session, NsUser user, Long amount) {
        this(null, false, LocalDateTime.now(), LocalDateTime.now(), session, user, amount, PENDING);
    }

    public Payment(String id, Session session, NsUser user, Long amount) {
        this(id, false, LocalDateTime.now(), LocalDateTime.now(), session, user, amount, PENDING);
    }

    public Payment(String id, boolean deleted, LocalDateTime createdAt, LocalDateTime updatedAt, Session session, NsUser user, Long amount, PaymentStatus status) {
        super(id, deleted, createdAt, updatedAt);
        this.session = session;
        this.user = user;
        this.amount = amount;
        this.status = status;
    }

    public boolean equalsSessionUser(Payment payment) {
        return payment.user.equals(this.user) && payment.session.equals(this.session);
    }

    public boolean isSameSession(Payment payment) {
        return payment.session.equals(this.session);
    }

    public boolean canEnroll(Session session, int enrollCount) {
        return session.canEnroll(enrollCount, amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Payment payment = (Payment) o;
        return Objects.equals(session, payment.session) &&
            Objects.equals(user, payment.user) &&
            Objects.equals(amount, payment.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), session, user, amount);
    }
}
