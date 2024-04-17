package nextstep.courses.domain.session.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SessionUsersTest {


    @DisplayName("선발인원에 대하여 수강승인/취소를 할 수 있다.")
    @Test
    void accessUser() {
        SessionUser sessionUser = new SessionUser(1L, 1L);
        SessionUser cancelSession = new SessionUser(2L, 2L);
        SessionUsers sessionUsers = SessionUsers.from(List.of(sessionUser,cancelSession));
        sessionUsers.accessSession(new SessionUser(1L, 1L));
        sessionUsers.cancelSession(new SessionUser(2L, 2L));

        Assertions.assertThat(sessionUser.getStatus()).isEqualTo(SessionUserStatus.ACCESS);
        Assertions.assertThat(cancelSession.getStatus()).isEqualTo(SessionUserStatus.CANCEL);
    }
}
