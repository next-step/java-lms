package nextstep.courses.domain.session;

import nextstep.courses.ExceedMaxAttendanceCountException;
import nextstep.courses.domain.session.SessionUsers;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionUsersTest {

    @DisplayName("최대 수강인원을 초과하게 된다면 에러를 발생시킵니다.")
    @Test
    void exceed() {
        // given
        SessionUsers sessionUsers = new SessionUsers(false, 1);
        sessionUsers.addUser(NsUserTest.JAVAJIGI);
        // when
        // then
        Assertions.assertThatThrownBy(() -> sessionUsers.addUser(NsUserTest.SANJIGI))
                .isInstanceOf(ExceedMaxAttendanceCountException.class)
                .hasMessage("이미 최대 수강 인원이 다 찼습니다.");
    }
}