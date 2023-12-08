package nextstep.session.domain;

import java.util.Objects;
import nextstep.users.domain.NsUser;

public class SessionPayment {

    private NsUser participant;

    private PaymentType paymentType;

    public SessionPayment() {
    }

    public SessionPayment(NsUser user, PaymentType paymentType) {
        this.participant = user;
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SessionPayment that = (SessionPayment) o;
        return Objects.equals(participant, that.participant) && paymentType == that.paymentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(participant, paymentType);
    }

    @Override
    public String toString() {
        return "SessionPayment{" +
                "participant=" + participant +
                ", paymentType=" + paymentType +
                '}';
    }
}
