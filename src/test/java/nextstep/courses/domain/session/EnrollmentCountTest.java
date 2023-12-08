package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class EnrollmentCountTest {
    @Test
    void isNoRemainingTest() {
        final EnrollmentCount enrollmentCount = new EnrollmentCount(3);
        assertThat(enrollmentCount.isNoRemaining()).isFalse();
    }

    @Test
    void decreaseTest() {
        final EnrollmentCount enrollmentCount = new EnrollmentCount(1);
        enrollmentCount.decrease();
        assertThat(enrollmentCount.isNoRemaining()).isTrue();
    }
}
