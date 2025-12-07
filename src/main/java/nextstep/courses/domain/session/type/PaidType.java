package nextstep.courses.domain.session.type;

import java.util.Objects;
import nextstep.courses.domain.registration.Registrations;

public class PaidType implements SessionType {
    private final long tuitionFee;
    private final Registrations registrations;

    public PaidType(int maxCapacity, long tuitionFee) {
        this(tuitionFee, new Registrations(maxCapacity));
    }

    public PaidType(long tuitionFee, Registrations registrations) {
        this.tuitionFee = tuitionFee;
        this.registrations = registrations;
    }

    @Override
    public void validateEnroll(long payAmount) {
        validateTuitionFee(payAmount);
        registrations.validateCapacity();
    }

    private void validateTuitionFee(long payAmount) {
        if (payAmount != tuitionFee) {
            throw new IllegalArgumentException("수강료와 지불한 금액이 정확히 일치해야 합니다.");
        }
    }

    public long getTuitionFee() {
        return tuitionFee;
    }

    @Override
    public Registrations getRegistrations() {
        return registrations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaidType that = (PaidType) o;
        return tuitionFee == that.tuitionFee
            && Objects.equals(registrations, that.registrations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tuitionFee, registrations);
    }
}