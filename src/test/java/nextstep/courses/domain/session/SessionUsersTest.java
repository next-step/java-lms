package nextstep.courses.domain.session;

import nextstep.courses.ExceedMaxAttendanceCountException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionUsersTest {

    @DisplayName("유료 강의 최대 수강인원을 초과하게 된다면 에러를 발생시킵니다.")
    @Test
    void exceed() {
        // given
        SessionUsers sessionUsers = new SessionUsers();
        sessionUsers.addUser(NsUserTest.JAVAJIGI, SessionType.notFreeSession(1));
        // when
        // then
        Assertions.assertThatThrownBy(() -> sessionUsers.addUser(NsUserTest.SANJIGI, SessionType.notFreeSession(1)))
                .isInstanceOf(ExceedMaxAttendanceCountException.class)
                .hasMessage("이미 최대 수강 인원이 다 찼습니다.");
    }

    @DisplayName("유료 강의 최대 수강인원 미만이라면 유저를 추가합니다.")
    @Test
    void addNotFreeUser() {
        // given
        SessionUsers sessionUsers = new SessionUsers();
        // when
        sessionUsers.addUser(NsUserTest.JAVAJIGI, SessionType.notFreeSession(1));
        // then
        Assertions.assertThat(sessionUsers.totalAttendUsersCount()).isEqualTo(1);
    }

    @DisplayName("무료 강의라면 유저를 추가합니다.")
    @Test
    void addUser() {
        // given
        SessionUsers sessionUsers = new SessionUsers();
        // when
        sessionUsers.addUser(NsUserTest.JAVAJIGI, SessionType.freeSession());
        // then
        Assertions.assertThat(sessionUsers.totalAttendUsersCount()).isEqualTo(1);
    }
}