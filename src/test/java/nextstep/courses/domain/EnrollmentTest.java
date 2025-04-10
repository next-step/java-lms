package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentTest {
    @Test
    @DisplayName("최대 수강 인원이 0보다 작으면 예외가 발생한다")
    void validateMaxEnrollment() {
        assertThatThrownBy(() -> new Enrollment(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 수강 인원은 0보다 커야 합니다.");
    }

    @Test
    @DisplayName("수강 신청을 하면 현재 수강 인원이 증가한다")
    void enroll() {
        // given
        Enrollment enrollment = new Enrollment(30);

        // when
        enrollment.enroll();

        // then
        assertThat(enrollment.getCurrentEnrollment()).isEqualTo(1);
    }

    @Test
    @DisplayName("최대 수강 인원을 초과하면 예외가 발생한다")
    void enrollExceedMaxEnrollment() {
        // given
        Enrollment enrollment = new Enrollment(1);

        // when
        enrollment.enroll();

        // then
        assertThatThrownBy(enrollment::enroll)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("최대 수강 인원을 초과했습니다.");
    }
} 