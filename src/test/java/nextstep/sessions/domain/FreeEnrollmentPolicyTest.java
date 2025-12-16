package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class FreeEnrollmentPolicyTest {

    @Test
    void validate_throwsException_whenCapacityIsLimited() {
        Capacity limitedCapacity = new Capacity(3, false);
        FreeEnrollmentPolicy policy = new FreeEnrollmentPolicy();
        assertThatThrownBy(() -> policy.validate(limitedCapacity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(FreeEnrollmentPolicy.ERROR_FREE_COURSE_CAPACITY_MUST_BE_UNLIMITED);
    }

    @Test
    void validate_passes_whenCapacityIsUnlimited() {
        Capacity unlimitedCapacity = CapacityTest.FREE_CAPACITY;
        FreeEnrollmentPolicy policy = new FreeEnrollmentPolicy();
        assertThatCode(() -> policy.validate(unlimitedCapacity))
                .doesNotThrowAnyException();
    }
}
