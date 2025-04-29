package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.SessionRecruitmentStatus;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class PaidEnrollmentsTest {
    private static final NsUser USER = new NsUser(1L, "user", "password", "name", "email");

    @Test
    @DisplayName("유료 강의의 수강 신청을 생성한다")
    void createPaidEnrollment() {
        // given
        int maxEnrollment = 30;

        // when
        Enrollments enrollments = new PaidEnrollments(maxEnrollment, new ArrayList<>(),
            SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);

        // then
        assertThat(enrollments).isNotNull();
    }

    @Test
    @DisplayName("유료 강의의 최대 수강 인원이 0이면 예외가 발생한다")
    void validateMaxEnrollment() {
        // given
        int maxEnrollment = 0;

        // when & then
        assertThatThrownBy(() -> new PaidEnrollments(maxEnrollment, new ArrayList<>(),
            SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유료 강의는 최대 수강 인원이 0보다 커야 합니다.");
    }

    @Test
    @DisplayName("유료 강의에 수강 신청을 한다")
    void enrollPaidSession() {
        // given
        Enrollments enrollments = new PaidEnrollments(30, new ArrayList<>(),
            SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);

        // when & then
        assertThatCode(() -> enrollments.enroll(USER)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("수강 인원이 가득 찬 유료 강의는 수강 신청이 불가능하다")
    void enrollFullPaidSession() {
        // given
        Enrollments enrollments = new PaidEnrollments(1, new ArrayList<>(),
            SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);
        NsUser anotherUser = new NsUser(2L, "user2", "password", "name", "email");

        // when
        enrollments.enroll(USER);

        // then
        assertThatThrownBy(() -> enrollments.enroll(anotherUser))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("이미 수강 신청한 사용자는 다시 수강 신청할 수 없다")
    void validateDuplicateEnrollment() {
        // given
        Enrollments enrollments = new PaidEnrollments(30, new ArrayList<>(),
            SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);

        // when
        enrollments.enroll(USER);

        // then
        assertThatThrownBy(() -> enrollments.enroll(USER))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("수강 신청할 사용자가 없으면 예외가 발생한다")
    void validateNullUser() {
        // given
        Enrollments enrollments = new PaidEnrollments(30, new ArrayList<>(),
            SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);

        // when & then
        assertThatThrownBy(() -> enrollments.enroll(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
} 