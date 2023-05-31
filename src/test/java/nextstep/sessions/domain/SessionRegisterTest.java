package nextstep.sessions.domain;

import nextstep.sessions.CannotRegisterException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SessionRegisterTest {

    @Test
    void 수강신청시_최대_수강_인원을_초과하면_예외를_던진다() {
        final var users = createUsers();
        final var sessionRegister = createSessionRegister(2, users);

        assertThrows(CannotRegisterException.class, () -> sessionRegister.register(new NsUser()));
    }

    @Test
    void 수강신청이_정상적으로_진행된다() {
        final var sessionRegister = new SessionRegister(5);

        sessionRegister.register(new NsUser());

        assertThat(sessionRegister.count()).isEqualTo(1);
    }

    private SessionRegister createSessionRegister(int maximumNumbers, List<NsUser> users) {
        return new SessionRegister(maximumNumbers, users);
    }

    private List<NsUser> createUsers() {
        return List.of(new NsUser(), new NsUser(), new NsUser());
    }

}
