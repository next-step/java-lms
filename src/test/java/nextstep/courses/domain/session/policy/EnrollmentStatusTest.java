package nextstep.courses.domain.session.policy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EnrollmentStatusTest {

    @DisplayName("비모집중일 때 수강신청이 불가능하다")
    @Test
    public void testCanEnroll_NotEnrolling() {
        assertThat(EnrollmentStatus.NOT_ENROLLING.canEnroll()).isFalse();
    }

    @DisplayName("모집중일 때 수강신청이 가능하다")
    @Test
    public void testCanEnroll_Enrolling() {
        assertThat(EnrollmentStatus.ENROLLING.canEnroll()).isTrue();
    }
}
