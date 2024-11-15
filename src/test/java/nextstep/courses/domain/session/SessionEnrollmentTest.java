package nextstep.courses.domain.session;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionEnrollmentTest {


    @DisplayName("동일한 유저를 중복 등록하려고 하면 예외가 발생한다.")
    @Test
    void enrollUserDoesNotAllowDuplicates() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(ProgressStatus.IN_PROGRESS, RecruitmentStatus.NOT_RECRUITING);
        sessionEnrollment.enrollUser(NsUserTest.SANJIGI);

        assertThatThrownBy(() -> sessionEnrollment.enrollUser(NsUserTest.SANJIGI))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("중복된 수강신청입니다.");
    }

    @DisplayName("진행 상태가 진행중이 아닌 경우를 알 수 있다.")
    @Test
    void isNotInProgress() {
        SessionEnrollment sessionEnrollmentOpen = SessionEnrollment.of(ProgressStatus.IN_PROGRESS, RecruitmentStatus.NOT_RECRUITING);
        assertThat(sessionEnrollmentOpen.isNotInProgress()).isFalse();

        SessionEnrollment sessionEnrollmentPrepare = SessionEnrollment.of(ProgressStatus.PREPARE, RecruitmentStatus.NOT_RECRUITING);
        assertThat(sessionEnrollmentPrepare.isNotInProgress()).isTrue();

        SessionEnrollment sessionEnrollmentCloses = SessionEnrollment.of(ProgressStatus.CLOSED, RecruitmentStatus.NOT_RECRUITING);
        assertThat(sessionEnrollmentCloses.isNotInProgress()).isTrue();
    }

    @DisplayName("모집 상태가 비모집중인 경우를 알 수 있다.")
    @Test
    void isNotRecruiting() {
        SessionEnrollment sessionEnrollmentOpen = SessionEnrollment.of(ProgressStatus.IN_PROGRESS, RecruitmentStatus.RECRUITING);
        assertThat(sessionEnrollmentOpen.isNotRecruiting()).isFalse();

        SessionEnrollment sessionEnrollmentPrepare = SessionEnrollment.of(ProgressStatus.IN_PROGRESS, RecruitmentStatus.NOT_RECRUITING);
        assertThat(sessionEnrollmentPrepare.isNotRecruiting()).isTrue();
    }

    @DisplayName("수강인원 초과여부를 알 수 있다.")
    @Test
    void isEnrollmentFullTest() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(ProgressStatus.CLOSED, RecruitmentStatus.RECRUITING);
        sessionEnrollment.enrollUser(NsUserTest.SANJIGI);
        sessionEnrollment.enrollUser(NsUserTest.JAVAJIGI);

        int MAX_ENROLLMENTS = 2;
        int EXCEEDED_ENROLLMENT_LIMIT = 3;

        assertThat(sessionEnrollment.isEnrollmentFull(MAX_ENROLLMENTS)).isTrue();
        assertThat(sessionEnrollment.isEnrollmentFull(EXCEEDED_ENROLLMENT_LIMIT)).isFalse();
    }


}