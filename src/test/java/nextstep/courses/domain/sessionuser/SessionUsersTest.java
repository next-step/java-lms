package nextstep.courses.domain.sessionuser;

import nextstep.courses.CanNotEnrollSameNsUserException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SessionUsersTest {

    @DisplayName("동일한 학생은 추가할 수 없습니다.")
    @Test
    void addSameStudent() {
        // given
        List<SessionUser> init = new ArrayList<>();
        init.add(new SessionUser(1L, 1L, UserType.STUDENT));
        SessionUsers sessionUsers = new SessionUsers(init);
        // when
        // then
        Assertions.assertThatThrownBy(() -> sessionUsers.add(new SessionUser(1L, 1L, UserType.STUDENT)))
                .isInstanceOf(CanNotEnrollSameNsUserException.class)
                .hasMessage("동일한 사람이 2번 신청할 수 없습니다.");
    }

    @DisplayName("찾는 사용자가 없으면 에러를 발생시킵니다.")
    @Test
    void noSessionUser() {
        // given
        List<SessionUser> init = new ArrayList<>();
        init.add(new SessionUser(1L, 1L, UserType.STUDENT));
        SessionUsers sessionUsers = new SessionUsers(init);
        // when
        // then
        Assertions.assertThatThrownBy(() -> sessionUsers.findSessionUser(2L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당하는 사용자가 없습니다.");
    }

    @DisplayName("특정 사용자 ID를 통해 해당 사용자를 찾을 수 있습니다.")
    @Test
    void findUser() {
        // given
        List<SessionUser> init = new ArrayList<>();
        init.add(new SessionUser(1L, 1L, UserType.STUDENT));
        SessionUsers sessionUsers = new SessionUsers(init);
        // when
        SessionUser sessionUser = sessionUsers.findSessionUser(1L);
        // then
        Assertions.assertThat(sessionUser.getUserId()).isEqualTo(1L);
        Assertions.assertThat(sessionUser.getSessionId()).isEqualTo(1L);
    }

}