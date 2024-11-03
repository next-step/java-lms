package nextstep.courses.domain.session;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionEnrollmentTest {


    @DisplayName("동일한 유저를 중복 등록하지 않는다.")
    @Test
    void enrollUserDoesNotAllowDuplicates() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.of(SessionStatus.OPEN);

        sessionEnrollment.enrollUser(NsUserTest.SANJIGI);
        sessionEnrollment.enrollUser(NsUserTest.SANJIGI);
        assertThat(sessionEnrollment.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("수강 상태가 모집중이 아닌 경우를 알 수 있다.")
    void isNotOpen() {
        SessionEnrollment sessionEnrollmentOpen = SessionEnrollment.of(SessionStatus.OPEN);
        assertThat(sessionEnrollmentOpen.isNotOpen()).isFalse();

        SessionEnrollment sessionEnrollmentPrepare = SessionEnrollment.of(SessionStatus.PREPARE);
        assertThat(sessionEnrollmentPrepare.isNotOpen()).isTrue();

        SessionEnrollment sessionEnrollmentCloses = SessionEnrollment.of(SessionStatus.CLOSED);
        assertThat(sessionEnrollmentCloses.isNotOpen()).isTrue();
    }

}