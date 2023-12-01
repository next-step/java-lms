package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
class SessionTest {

    @Test
    void 강의는_모집중일때_신청_가능하다() throws CannotRegisterException {
        Session session = new Session(SessionStatus.OPEN);
        assertThat(session.register(NsUser.GUEST_USER)).contains(NsUser.GUEST_USER);
    }

    @Test
    void 강의는_모집중이_아니면_신청_불가능하다() {
        Session session = new Session(SessionStatus.WAITING);
        assertThatThrownBy(() -> {
            session.register(NsUser.GUEST_USER);
        }).isInstanceOf(CannotRegisterException.class);
    }
}