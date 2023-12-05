package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class EnrollmentCountTest {

    @Test
    void isNotRemainTest() {
        final EnrollmentCount enrollmentCount = new EnrollmentCount(3);
        assertThat(enrollmentCount.isNotRemain()).isFalse();
    }

    @Test
    void decreaseTest() {
        final EnrollmentCount enrollmentCount = new EnrollmentCount(1);
        enrollmentCount.decrease();
        assertThat(enrollmentCount.isNotRemain()).isTrue();
    }
}