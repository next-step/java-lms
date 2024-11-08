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
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(SessionStatus.OPEN);
        sessionEnrollment.enrollUser(NsUserTest.SANJIGI);

        assertThatThrownBy(() -> sessionEnrollment.enrollUser(NsUserTest.SANJIGI))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("중복된 수강신청입니다.");
    }

    @DisplayName("수강 상태가 모집중이 아닌 경우를 알 수 있다.")
    @Test
    void isNotOpen() {
        SessionEnrollment sessionEnrollmentOpen = SessionEnrollment.of(SessionStatus.OPEN);
        assertThat(sessionEnrollmentOpen.isNotOpen()).isFalse();

        SessionEnrollment sessionEnrollmentPrepare = SessionEnrollment.of(SessionStatus.PREPARE);
        assertThat(sessionEnrollmentPrepare.isNotOpen()).isTrue();

        SessionEnrollment sessionEnrollmentCloses = SessionEnrollment.of(SessionStatus.CLOSED);
        assertThat(sessionEnrollmentCloses.isNotOpen()).isTrue();
    }

    @DisplayName("수강인원 초과여부를 알 수 있다.")
    @Test
    void isEnrollmentFullTest() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(SessionStatus.OPEN);
        sessionEnrollment.enrollUser(NsUserTest.SANJIGI);
        sessionEnrollment.enrollUser(NsUserTest.JAVAJIGI);

        int MAX_ENROLLMENTS = 2;
        int EXCEEDED_ENROLLMENT_LIMIT = 3;

        assertThat(sessionEnrollment.isEnrollmentFull(MAX_ENROLLMENTS)).isTrue();
        assertThat(sessionEnrollment.isEnrollmentFull(EXCEEDED_ENROLLMENT_LIMIT)).isFalse();
    }


}