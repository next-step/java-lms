package nextstep.session.infrastructure;

import nextstep.session.domain.Session;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.Subscriber;
import nextstep.session.domain.SubscriberRepository;
import nextstep.session.domain.image.Image;
import nextstep.support.TestSupport;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class JdbcSubscriberRepositoryTest extends TestSupport {

    public static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");

    @Autowired
    private SubscriberRepository subscriberRepository;
    @Autowired
    private SessionRepository sessionRepository;

    @DisplayName("강의 수강자를 저장한 후 조회한다.")
    @Test
    void saveTest() {
        //given
        LocalDateTime startDate = LocalDateTime.parse("2023-04-05T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2023-05-05T00:00:00");

        Image image = new Image("테스트이미지.jpg", 300, 200, 1);
        Session session = Session.createPaid(1L, "테스트강의", image, 1, 800000, startDate, endDate);

        sessionRepository.save(session);

        Session findSession = sessionRepository.findById(1L);

        Subscriber subscriber = new Subscriber(findSession, JAVAJIGI);

        //when, then
        subscriberRepository.save(subscriber);
        assertThat(subscriberRepository.findById(1L))
                .extracting("session.id", "nsUser.id")
                .containsExactly(findSession.getId(), JAVAJIGI.getId());
    }
}
