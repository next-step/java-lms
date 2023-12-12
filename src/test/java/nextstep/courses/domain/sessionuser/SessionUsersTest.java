package nextstep.courses.domain.sessionuser;

import nextstep.courses.CanNotEnrollSameNsUserException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

}