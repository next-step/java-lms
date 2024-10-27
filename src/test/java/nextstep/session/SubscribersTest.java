package nextstep.session;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class SubscribersTest {

    public static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");

    @DisplayName("강의 신청자를 추가한다.")
    @Test
    void addUserTest() {
        Subscribers subscribers = new Subscribers();
        subscribers.addUser(JAVAJIGI);

        assertThat(subscribers.getSubscribeUsers())
                .extracting("id", "userId", "password", "name", "email")
                .contains(tuple(1L, "javajigi", "password", "name", "javajigi@slipp.net"));
    }

    @DisplayName("강의 신청자들의 전체 인원수를 가져온다.")
    @Test
    void subscribeUsersSizeTest() {
        Subscribers subscribers = new Subscribers();
        subscribers.addUser(JAVAJIGI);

        assertThat(subscribers.subscribeUsersSize()).isEqualTo(1);
    }
}
