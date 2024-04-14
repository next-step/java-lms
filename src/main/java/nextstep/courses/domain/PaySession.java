package nextstep.courses.domain;

import java.util.Objects;

import nextstep.payments.domain.Payment;

public class PaySession {

    private final Session session;
    private int joinLimit;

    public PaySession(Session session) {
        this.session = session;
    }

    public void join(JoinUser joinUser, Payment payment) {
        validPaid(payment);
        session.join(joinUser);
    }

    private void validPaid(Payment payment) {
        if(!payment.isPaid())
            throw new IllegalStateException("유료 강의의 지불이 완료되지 않았습니다.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaySession session1 = (PaySession) o;
        return Objects.equals(session, session1.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session);
    }
}
