package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.fixture.SessionFixture;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SessionServiceTest {
    @Autowired
    private SessionService sessionService;

    @Test
    void save() {
        Session session = SessionFixture.create(SessionStatus.PREPARING, 1);

        Session savedSession = sessionService.save(session, 1L);
        Session findSession = sessionService.findById(savedSession.getId());

        assertThat(findSession.getId()).isEqualTo(savedSession.getId());
    }

    @Test
    void findByCourseId() {
        sessionService.save(SessionFixture.create(SessionStatus.PREPARING, 1), 2L);
        sessionService.save(SessionFixture.create(SessionStatus.PREPARING, 1), 2L);
        List<Session> findSession = sessionService.findByCourseId(2L);

        assertThat(findSession).hasSize(2);
    }

    @Test
    void saveSessionUser() {
        Session session = sessionService.save(SessionFixture.create(SessionStatus.RECRUITING, 1), 1L);

        sessionService.saveSessionUser(session, NsUserTest.JAVAJIGI);
        List<NsUser> sessionUsers = sessionService.findAllUserBySessionId(session.getId());

        assertThat(sessionUsers).hasSize(1);
        assertThat(sessionUsers.get(0).getUserId()).isEqualTo(NsUserTest.JAVAJIGI.getUserId());
    }
}
