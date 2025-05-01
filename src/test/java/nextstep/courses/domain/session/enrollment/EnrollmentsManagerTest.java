package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.SessionRecruitmentStatus;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentsManagerTest {
    private NsUser user;

    @BeforeEach
    void setUp() {
        user = new NsUser(1L, "user", "password", "name", "user@email.com");
    }

    @Test
    @DisplayName("모집중인 상태에서 수강신청을 하면 성공한다")
    void enroll_success_when_recruiting() {
        // given
        EnrollmentManager enrollmentManager = new EnrollmentManager(
            SessionProgressStatus.PREPARING,
            SessionRecruitmentStatus.RECRUITING
        );

        // when
        enrollmentManager.enroll(user);

        // then
        assertThat(enrollmentManager.getEnrolledUsers()).isEmpty();
        assertThat(enrollmentManager.getPendingApprovalUsers()).hasSize(1);
        assertThat(enrollmentManager.getPendingApprovalUsers().get(0)).isEqualTo(user);
        assertThat(enrollmentManager.getEnrollmentStatus(user)).isEqualTo(EnrollmentStatus.PENDING_APPROVAL);
    }

    @Test
    @DisplayName("비모집중인 상태에서 수강신청을 하면 실패한다")
    void enroll_fail_when_not_recruiting() {
        // given
        EnrollmentManager enrollmentManager = new EnrollmentManager(
            SessionProgressStatus.PREPARING,
            SessionRecruitmentStatus.NOT_RECRUITING
        );

        // when & then
        assertThatThrownBy(() -> enrollmentManager.enroll(user))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("수강 신청이 불가능합니다.");
    }
}
