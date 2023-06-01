package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionEnrollmentStatus.ENROLLMENT;
import static nextstep.courses.domain.SessionEnrollmentStatus.NON_ENROLLMENT;
import static org.assertj.core.api.Assertions.assertThat;

class SessionEnrollmentStatusTest {
    @Test
    void find() {
        String enrollmentStatus1 = "모집";
        String enrollmentStatus2 = "비모집";
        assertThat(SessionEnrollmentStatus.find(ENROLLMENT.name())).isEqualTo(ENROLLMENT);
        assertThat(SessionEnrollmentStatus.find(NON_ENROLLMENT.name())).isEqualTo(SessionEnrollmentStatus.NON_ENROLLMENT);
    }
}
