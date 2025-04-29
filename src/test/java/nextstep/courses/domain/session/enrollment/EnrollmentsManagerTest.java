package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.SessionRecruitmentStatus;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentsManagerTest {
    private List<NsUser> enrolledUsers;
    private NsUser user;

    @BeforeEach
    void setUp() {
        enrolledUsers = new ArrayList<>();
        user = new NsUser(1L, "user", "password", "name", "user@email.com");
    }

    @Test
    @DisplayName("모집중인 상태에서 수강신청을 하면 성공한다")
    void enroll_success_when_recruiting() {
        // given
        EnrollmentManager enrollmentManager = new EnrollmentManager(
            enrolledUsers,
            SessionProgressStatus.PREPARING,
            SessionRecruitmentStatus.RECRUITING
        );

        // when
        enrollmentManager.enroll(user);

        // then
        assertThat(enrolledUsers).hasSize(1);
        assertThat(enrolledUsers.get(0)).isEqualTo(user);
    }

    @Test
    @DisplayName("비모집중인 상태에서 수강신청을 하면 실패한다")
    void enroll_fail_when_not_recruiting() {
        // given
        EnrollmentManager enrollmentManager = new EnrollmentManager(
            enrolledUsers,
            SessionProgressStatus.PREPARING,
            SessionRecruitmentStatus.NOT_RECRUITING
        );

        // when & then
        assertThatThrownBy(() -> enrollmentManager.enroll(user))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("수강 신청이 불가능합니다.");
    }
} 