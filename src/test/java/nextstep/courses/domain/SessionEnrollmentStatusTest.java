package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionEnrollmentStatusTest {
    @Test
    void find() {
        String enrollmentStatus1 = "모집";
        String enrollmentStatus2 = "비모집";
        assertThat(SessionEnrollmentStatus.find(enrollmentStatus1)).isEqualTo(SessionEnrollmentStatus.ENROLLMENT);
        assertThat(SessionEnrollmentStatus.find(enrollmentStatus2)).isEqualTo(SessionEnrollmentStatus.NON_ENROLLMENT);
    }
}
