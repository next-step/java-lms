package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentAvailabilityPolicyTest {

    @Test
    @DisplayName("진행중이거나 모집중이면 등록 가능하다")
    void enroll_success() {
        // 진행중
        EnrollmentAvailabilityPolicy enrollmentAvailabilityPolicy = new EnrollmentAvailabilityPolicy();
        assertThatCode(() -> enrollmentAvailabilityPolicy.validate(SessionProgress.IN_PROGRESS, EnrollmentStatus.CLOSED))
                .doesNotThrowAnyException();

        // 모집중
        assertThatCode(() -> enrollmentAvailabilityPolicy.validate(SessionProgress.FINISHED, EnrollmentStatus.OPEN))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("진행중이거나 모집중이 아니면 등록할 수 없다")
    void enroll_fail() {
        EnrollmentAvailabilityPolicy enrollmentAvailabilityPolicy = new EnrollmentAvailabilityPolicy();
        assertThatThrownBy(() -> enrollmentAvailabilityPolicy.validate(SessionProgress.READY, EnrollmentStatus.CLOSED))
                .isInstanceOf(IllegalStateException.class);

        assertThatThrownBy(() -> enrollmentAvailabilityPolicy.validate(SessionProgress.FINISHED, EnrollmentStatus.CLOSED))
                .isInstanceOf(IllegalStateException.class);
    }


}