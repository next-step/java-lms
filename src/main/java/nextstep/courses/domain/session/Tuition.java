package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

import java.util.Objects;

public class Tuition {

    private Long value;

    public Tuition(Long value) {
        this.value = value;
    }

    public boolean matchAmount(Payment payment) {
        return this.value == payment.getAmount();
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tuition tuition = (Tuition) o;
        return Objects.equals(value, tuition.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
