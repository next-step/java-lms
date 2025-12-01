package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Enrollment {

    private final NsUser user;
    private final Long sessionId;
    private final Payment payment;

    public Enrollment(NsUser user, Long sessionId, Payment payment) {
        this.user = user;
        this.sessionId = sessionId;
        this.payment = payment;
    }

    public void isPaymentAmount(SessionPolicy sessionPolicy) {
        if(sessionPolicy.matchAmount(payment)){
            throw new IllegalArgumentException("강의 금액과 결제 금액이 일치하지 않습니다.");
        }
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

}
