package nextstep.courses.domain.session.enrollment;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentTest {
    private static final NsUser USER = new NsUser(1L, "user", "password", "name", "email");

    @Test
    @DisplayName("무료 강의의 수강 신청을 생성한다")
    void createFreeEnrollment() {
        // given
        Enrollment enrollment = new FreeEnrollment();

        // when & then
        assertThat(enrollment).isNotNull();
    }

    @Test
    @DisplayName("유료 강의의 수강 신청을 생성한다")
    void createPaidEnrollment() {
        // given
        int maxEnrollment = 30;

        // when
        Enrollment enrollment = new PaidEnrollment(maxEnrollment);

        // then
        assertThat(enrollment).isNotNull();
    }

    @Test
    @DisplayName("유료 강의의 최대 수강 인원이 0이면 예외가 발생한다")
    void validateMaxEnrollment() {
        // given
        int maxEnrollment = 0;

        // when & then
        assertThatThrownBy(() -> new PaidEnrollment(maxEnrollment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유료 강의는 최대 수강 인원이 0보다 커야 합니다.");
    }

    @Test
    @DisplayName("무료 강의에 수강 신청을 한다")
    void enrollFreeSession() {
        // given
        Enrollment enrollment = new FreeEnrollment();
        NsUser anotherUser = new NsUser(2L, "user2", "password", "name", "email");

        // when
        enrollment.enroll(USER);
        enrollment.enroll(anotherUser);

        // then
        assertThat(enrollment.isFull()).isFalse();
        assertThat(enrollment.hasEnrolledUser(USER)).isTrue();
        assertThat(enrollment.hasEnrolledUser(anotherUser)).isTrue();
    }

    @Test
    @DisplayName("유료 강의에 수강 신청을 한다")
    void enrollPaidSession() {
        // given
        Enrollment enrollment = new PaidEnrollment(30);

        // when
        enrollment.enroll(USER);

        // then
        assertThat(enrollment.hasEnrolledUser(USER)).isTrue();
    }

    @Test
    @DisplayName("수강 인원이 가득 찬 유료 강의는 수강 신청이 불가능하다")
    void enrollFullPaidSession() {
        // given
        Enrollment enrollment = new PaidEnrollment(1);
        NsUser anotherUser = new NsUser(2L, "user2", "password", "name", "email");

        // when
        enrollment.enroll(USER);

        // then
        assertThat(enrollment.isFull()).isTrue();
        assertThatThrownBy(() -> enrollment.enroll(anotherUser))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("이미 수강 신청한 사용자는 다시 수강 신청할 수 없다")
    void validateDuplicateEnrollment() {
        // given
        Enrollment enrollment = new FreeEnrollment();

        // when
        enrollment.enroll(USER);

        // then
        assertThatThrownBy(() -> enrollment.enroll(USER))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("수강 신청할 사용자가 없으면 예외가 발생한다")
    void validateNullUser() {
        // given
        Enrollment enrollment = new FreeEnrollment();

        // when & then
        assertThatThrownBy(() -> enrollment.enroll(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
} 