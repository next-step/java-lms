package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentTest {
    private static final NsUser USER = new NsUser(1L, "user", "password", "name", "email");

    @Test
    @DisplayName("무료 강의는 수강 인원 제한이 없어야 한다")
    void free_session_has_no_enrollment_limit() {
        Enrollment enrollment = Enrollment.free();
        enrollment.enroll(USER);
        assertThat(enrollment.getCurrentEnrollment()).isEqualTo(1);
    }

    @Test
    @DisplayName("유료 강의는 수강 인원 제한이 있어야 한다")
    void paid_session_has_enrollment_limit() {
        Enrollment enrollment = Enrollment.paid(1);
        enrollment.enroll(USER);
        assertThat(enrollment.getCurrentEnrollment()).isEqualTo(1);
    }

    @Test
    @DisplayName("유료 강의는 수강 인원 제한을 초과할 수 없다")
    void paid_session_cannot_exceed_enrollment_limit() {
        Enrollment enrollment = Enrollment.paid(1);
        enrollment.enroll(USER);
        assertThatThrownBy(() -> enrollment.enroll(new NsUser(2L, "user2", "password", "name", "email")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("최대 수강 인원을 초과했습니다.");
    }

    @Test
    @DisplayName("유료 강의는 수강 인원 제한이 0보다 커야 한다")
    void paid_session_must_have_positive_enrollment_limit() {
        assertThatThrownBy(() -> Enrollment.paid(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유료 강의는 최대 수강 인원이 0보다 커야 합니다.");
    }
} 