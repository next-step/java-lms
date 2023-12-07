package nextstep.session;

import nextstep.session.domain.SessionType;
import nextstep.session.domain.Users;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class UsersTest {

    @Test
    void 유료강의는_최대_수강_인원을_초과할_수_없다() {
        Users users = new Users(0, Set.of());

        Throwable throwable = catchThrowable(() -> users.register(NsUserTest.JAVAJIGI, SessionType.PAID));

        assertThat(throwable)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("최대 수강 인원을 초과하였습니다.");
    }
}
