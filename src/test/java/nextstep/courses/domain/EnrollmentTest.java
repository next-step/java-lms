package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentTest {
    private static final NsUser USER = new NsUser(1L, "user@email.com", "password", "name", "010-1234-5678");

    @Test
    @DisplayName("최대 수강 인원이 음수이면 예외가 발생한다")
    void negativeMaxEnrollment() {
        assertThatThrownBy(() -> new Enrollment(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 수강 인원은 0보다 커야 합니다.");
    }

    @Test
    @DisplayName("수강 신청을 한다")
    void enroll() {
        // given
        Enrollment enrollment = new Enrollment(30);

        // when
        enrollment.enroll(USER);

        // then
        assertThat(enrollment.getCurrentEnrollment()).isEqualTo(1);
        assertThat(enrollment.getEnrolledUsers()).hasSize(1);
        assertThat(enrollment.getEnrolledUsers().get(0)).isEqualTo(USER);
    }

    @Test
    @DisplayName("최대 수강 인원을 초과하면 예외가 발생한다")
    void exceedMaxEnrollment() {
        // given
        Enrollment enrollment = new Enrollment(1);
        enrollment.enroll(USER);

        // when & then
        assertThatThrownBy(() -> enrollment.enroll(USER))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("최대 수강 인원을 초과했습니다.");
    }
} 