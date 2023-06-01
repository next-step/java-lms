package nextstep.courses.domain;

import nextstep.courses.domain.fixture.SessionFixtures;
import nextstep.courses.exception.CannotRegisterException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class SessionUsersTest {

    @Test
    public void 유저_등록이_잘_동작해야_한다() throws Exception {
        //given
        SessionUsers users = new SessionUsers(2);

        //when
        users.add(SessionFixtures.USER_1);
        users.add(SessionFixtures.USER_2);

        //then
        assertThat(users.count()).isEqualTo(2);
    }

    @Test
    public void 이미_등록한_유저는_등록할_수_없다() throws Exception {
        //given
        SessionUsers users = new SessionUsers(2);

        //when
        users.add(SessionFixtures.USER_1);

        //then
        assertThatThrownBy(() -> users.add(SessionFixtures.USER_1))
                .isInstanceOf(CannotRegisterException.class);
    }

    @Test
    public void 최대_수강_인원을_초과할_수_없다() throws Exception {
        //given
        SessionUsers users = new SessionUsers(1);

        //when
        users.add(SessionFixtures.USER_1);

        //then
        assertThatThrownBy(() -> users.add(SessionFixtures.USER_2))
                .isInstanceOf(CannotRegisterException.class);
    }

    @Test
    public void 현재_수강_인원보다_제한_인원을_작게_설정할_수_없다() throws Exception {
        //given
        SessionUsers users = new SessionUsers(2);

        //when
        users.add(SessionFixtures.USER_1);
        users.add(SessionFixtures.USER_2);

        //then
        assertThatThrownBy(() -> users.updateCapacity(1))
                .isInstanceOf(IllegalStateException.class);
    }
}