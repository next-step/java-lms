package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.SessionRecruitmentStatus;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

class FreeEnrollmentsTest {
    private static final NsUser USER = new NsUser(1L, "user", "password", "name", "email");

    @Test
    @DisplayName("무료 강의의 수강 신청을 생성한다")
    void createFreeEnrollment() {
        // given
        Enrollments enrollments = new FreeEnrollments(new ArrayList<>(),
            SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);

        // when & then
        assertThat(enrollments).isNotNull();
    }

    @Test
    @DisplayName("무료 강의에 수강 신청을 한다")
    void enrollFreeSession() {
        // given
        Enrollments enrollments = new FreeEnrollments(new ArrayList<>(),
            SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);
        NsUser anotherUser = new NsUser(2L, "user2", "password", "name", "email");

        // when & then
        assertThatCode(() -> {
            enrollments.enroll(USER);
            enrollments.enroll(anotherUser);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이미 수강 신청한 사용자는 다시 수강 신청할 수 없다")
    void validateDuplicateEnrollment() {
        // given
        Enrollments enrollments = new FreeEnrollments(new ArrayList<>(),
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
        Enrollments enrollments = new FreeEnrollments(new ArrayList<>(),
            SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);

        // when & then
        assertThatThrownBy(() -> enrollments.enroll(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
} 