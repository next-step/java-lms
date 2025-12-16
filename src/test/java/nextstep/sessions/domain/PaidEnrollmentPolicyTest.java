package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class PaidEnrollmentPolicyTest {

    @Test
    void validate_throwsException_whenCapacityIsUnlimited() {
        Capacity unlimitedCapacity = CapacityTest.FREE_CAPACITY;
        PaidEnrollmentPolicy policy = new PaidEnrollmentPolicy(1000);
        assertThatThrownBy(() -> policy.validate(unlimitedCapacity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PaidEnrollmentPolicy.ERROR_PAID_SESSION_CAPACITY_REQUIRED);
    }

    @Test
    void validate_passes_whenCapacityIsLimited() {
        Capacity limitedCapacity = new Capacity(3, false);
        PaidEnrollmentPolicy policy = new PaidEnrollmentPolicy(1000);
        assertThatCode(() -> policy.validate(limitedCapacity))
                .doesNotThrowAnyException();
    }
}
