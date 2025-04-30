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
        enrollments.approve(USER);

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

    @Test
    @DisplayName("유료 강의 수강 신청 시 초기 상태는 승인 대기 상태이다")
    void enrollmentInitialStatusIsPendingApproval() {
        // given
        Enrollments enrollments = new PaidEnrollments(30, new ArrayList<>(),
            SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);

        // when
        enrollments.enroll(USER);

        // then
        assertThat(enrollments.getEnrollmentStatus(USER)).isEqualTo(EnrollmentStatus.PENDING_APPROVAL);
        assertThat(enrollments.getPendingApprovalUsers()).hasSize(1);
        assertThat(enrollments.getPendingApprovalUsers().get(0)).isEqualTo(USER);
    }

    @Test
    @DisplayName("유료 강의 승인 대기 상태의 수강 신청을 승인한다")
    void approveEnrollment() {
        // given
        Enrollments enrollments = new PaidEnrollments(30, new ArrayList<>(),
            SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);
        enrollments.enroll(USER);

        // when
        enrollments.approve(USER);

        // then
        assertThat(enrollments.getEnrollmentStatus(USER)).isEqualTo(EnrollmentStatus.ENROLLED);
        assertThat(enrollments.getEnrolledUsers()).hasSize(1);
        assertThat(enrollments.getEnrolledUsers().get(0)).isEqualTo(USER);
    }

    @Test
    @DisplayName("유료 강의 승인 대기 상태가 아닌 수강 신청을 승인하면 예외가 발생한다")
    void approveNonPendingEnrollment() {
        // given
        Enrollments enrollments = new PaidEnrollments(30, new ArrayList<>(),
            SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);
        enrollments.enroll(USER);
        enrollments.approve(USER);

        // when & then
        assertThatThrownBy(() -> enrollments.approve(USER))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("승인 대기 상태의 수강신청만 승인할 수 있습니다.");
    }

    @Test
    @DisplayName("유료 강의 수강 신청을 취소한다")
    void cancelEnrollment() {
        // given
        Enrollments enrollments = new PaidEnrollments(30, new ArrayList<>(),
            SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);
        enrollments.enroll(USER);

        // when
        enrollments.cancel(USER);

        // then
        assertThat(enrollments.getEnrollmentStatus(USER)).isEqualTo(EnrollmentStatus.CANCELLED);
    }

    @Test
    @DisplayName("유료 강의 승인된 수강 신청도 취소할 수 있다")
    void cancelApprovedEnrollment() {
        // given
        Enrollments enrollments = new PaidEnrollments(30, new ArrayList<>(),
            SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);
        enrollments.enroll(USER);
        enrollments.approve(USER);

        // when
        enrollments.cancel(USER);

        // then
        assertThat(enrollments.getEnrollmentStatus(USER)).isEqualTo(EnrollmentStatus.CANCELLED);
    }

    @Test
    @DisplayName("유료 강의 수강 인원 계산 시 승인된 수강 신청만 포함한다")
    void enrollmentCountOnlyIncludesApproved() {
        // given
        Enrollments enrollments = new PaidEnrollments(2, new ArrayList<>(),
            SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);
        NsUser user2 = new NsUser(2L, "user2", "password", "name", "email");
        NsUser user3 = new NsUser(3L, "user3", "password", "name", "email");

        // when
        enrollments.enroll(USER);
        enrollments.enroll(user2);
        enrollments.approve(USER);

        // then
        assertThatCode(() -> enrollments.enroll(user3)).doesNotThrowAnyException();
    }
}
