package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SessionUsersTest {
    @Test
    @DisplayName("승인된 사용자 강의 수강 신청 시 수강인원이 1 증가한다")
    void enroll() {
        SessionUsers sessionUsers = new SessionUsers(1);
        SessionUser sessionUser = new SessionUser(NsUserTest.JAVAJIGI.getId());
        sessionUser.approve();
        sessionUsers.enroll(sessionUser);
        assertThat(sessionUsers.enrollmentCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("강의 최대 수강 인원을 초과할 수 없다")
    void enrollFail() {
        SessionUsers sessionUsers = new SessionUsers(0);
        SessionUser sessionUser = new SessionUser(NsUserTest.JAVAJIGI.getId());

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> sessionUsers.enroll(sessionUser))
                .withMessageMatching(SessionUsers.MAXIMUM_ENROLLMENT_MESSAGE);
    }

    @Test
    @DisplayName("이미 수강신청한 사용자의 경우 신청할 수 없다")
    void validate_duplicate() {
        SessionUsers sessionUsers = new SessionUsers(10);
        SessionUser sessionUser = new SessionUser(NsUserTest.JAVAJIGI.getId());
        sessionUsers.enroll(sessionUser);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> sessionUsers.enroll(sessionUser))
                .withMessageMatching(SessionUsers.ALREADY_ENROLLED_USER);
    }
}
