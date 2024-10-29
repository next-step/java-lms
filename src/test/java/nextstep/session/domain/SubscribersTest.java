package nextstep.session.domain;

import nextstep.session.domain.image.Image;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class SubscribersTest {

    public static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");

    @DisplayName("강의 신청자를 추가한다.")
    @Test
    void addUserTest() {
        //given
        Subscribers subscribers = new Subscribers();
        LocalDateTime startDate = LocalDateTime.parse("2023-04-05T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2023-05-05T00:00:00");

        Image image = new Image("테스트이미지.jpg", 300, 200, 1);
        Session session = Session.createPaid(1L, "테스트강의", image, 1, 800000, startDate, endDate);

        //when
        subscribers.addUser(session, JAVAJIGI);
        //then
        assertThat(subscribers.getSubscribeUsers())
                .extracting("nsUser.id", "nsUser.userId", "nsUser.password", "nsUser.name", "nsUser.email")
                .contains(tuple(1L, "javajigi", "password", "name", "javajigi@slipp.net"));
    }

    @DisplayName("강의 신청자들의 전체 인원수를 가져온다.")
    @Test
    void subscribeUsersSizeTest() {
        //given
        Subscribers subscribers = new Subscribers();
        LocalDateTime startDate = LocalDateTime.parse("2023-04-05T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2023-05-05T00:00:00");

        Image image = new Image("테스트이미지.jpg", 300, 200, 1);
        Session session = Session.createPaid(1L, "테스트강의", image, 1, 800000, startDate, endDate);

        //when
        subscribers.addUser(session, JAVAJIGI);

        //then
        assertThat(subscribers.subscribeUsersSize()).isEqualTo(1);
    }
}
