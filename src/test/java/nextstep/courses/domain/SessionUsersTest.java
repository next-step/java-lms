package nextstep.courses.domain;

import nextstep.courses.exception.SessionUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionUsersTest {

    @Test
    @DisplayName("성공 - 유저가 강의 신청을 처음 할 경우 예외가 발생 하지 않는다. ")
    void success_session_user_add() {
        SessionUsers sessionUsers = new SessionUsers();
        assertThatCode(() -> sessionUsers.addUser(JAVAJIGI))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 강의를 이미 신청한 유저는 중복으로 신청할 수 없다. ")
    void fail_session_user_add_duplicate() {
        SessionUsers sessionUsers = new SessionUsers(
                Set.of(JAVAJIGI)
        );

        assertThatThrownBy(() -> sessionUsers.addUser(JAVAJIGI))
                .isInstanceOf(SessionUserException.class)
                .hasMessage("강의를 이미 신청한 유저이므로 중복으로 신청할 수 없습니다.");
    }

}
