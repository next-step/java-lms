package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Enrollment {

    private final Long id;
    private final NsUser user;
    private final Long sessionId;
    private final Payment payment;

    public Enrollment(NsUser user, Long sessionId, Payment payment) {
        this(0L, user, sessionId, payment);
    }

    public Enrollment(Long id, NsUser user, Long sessionId, Payment payment) {
        this.id = id;
        this.user = user;
        this.sessionId = sessionId;
        this.payment = payment;
    }

    public void isPaymentAmount(SessionPolicy sessionPolicy) {
        if(!sessionPolicy.matchAmount(this.payment)){
            throw new IllegalArgumentException("강의 금액과 결제 금액이 일치하지 않습니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public NsUser getUser() {
        return user;
    }

    public Long getSessionId() {
        return sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(user.getId(), that.user.getId()) && Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, sessionId, payment);
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", user=" + user +
                ", sessionId=" + sessionId +
                ", payment=" + payment +
                '}';
    }
}
