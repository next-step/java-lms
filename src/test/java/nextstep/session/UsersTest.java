package nextstep.session;

import nextstep.session.domain.Session;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static nextstep.session.TestFixtures.recrutingPaidSession;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UsersTest {

    @Test
    void 유료강의는_최대_수강_인원을_초과할_수_없다() {
        Session recrutingPaidSession = recrutingPaidSession();
        assertThatThrownBy(() -> recrutingPaidSession.register(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("최대 수강 인원을 초과하였습니다.");
    }
}
