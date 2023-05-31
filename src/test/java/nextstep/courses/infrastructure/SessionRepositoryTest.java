package nextstep.courses.infrastructure;

import nextstep.courses.domain.NsUsers;
import nextstep.courses.domain.Session;
import nextstep.courses.enums.SessionStatus;
import nextstep.courses.service.SessionService;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SessionRepositoryTest {
    @Autowired
    private SessionService sessionService;

    @Test
    void save() {
        Session session = create(SessionStatus.PREPARING, 1);

        Session savedSession = sessionService.save(session);
        Session findSession = sessionService.findById(savedSession.getId());

        assertThat(findSession.getId()).isEqualTo(savedSession.getId());
    }

    @Test
    void enroll() {
        Session session = sessionService.save(create(SessionStatus.RECRUITING, 1));

        sessionService.enroll(session, NsUserTest.JAVAJIGI);
        List<NsUser> sessionUsers = sessionService.findAllUserBySessionId(session.getId());

        assertThat(sessionUsers.get(0).getUserId()).isEqualTo(NsUserTest.JAVAJIGI.getUserId());
    }

    public static Session create(SessionStatus sessionStatus, int maxNsUserCount) {
        return new Session(1L, 1L, LocalDateTime.now(), LocalDateTime.now().plusMonths(1), "https://test.png", true, sessionStatus, new NsUsers(1L), maxNsUserCount);
    }
}
