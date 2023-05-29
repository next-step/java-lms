package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.fixture.SessionFixture;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void save() {
        Session session = SessionFixture.createRecruitingSession();
        Session savedSession = sessionRepository.save(session, 1L);
        assertThat(savedSession).isNotNull();
    }

    @Test
    void findById() {
        Session session = SessionFixture.createRecruitingSession();
        Session savedSession = sessionRepository.save(session, 1L);

        Session findSession = sessionRepository.findById(savedSession.getId());
        assertThat(findSession.getId()).isEqualTo(savedSession.getId());
    }

    @Test
    void findByCourseId() {
        sessionRepository.save(SessionFixture.createRecruitingSession(), 1L);
        sessionRepository.save(SessionFixture.createRecruitingSession(), 1L);

        List<Session> findSessions = sessionRepository.findByCourseId(1L);
        assertThat(findSessions).hasSize(2);
    }

    @Test
    void saveSessionUser() {
        NsUser nsUser = NsUserTest.JAVAJIGI;
        Session session = SessionFixture.createRecruitingSession();

        long id = sessionRepository.saveSessionUser(session, new SessionUser(nsUser));
        assertThat(id).isPositive();
    }

    @Test
    void findAllUserBySessionId() {
        Session savedSession = sessionRepository.save(SessionFixture.createRecruitingSession(), 1L);
        sessionRepository.saveSessionUser(savedSession, new SessionUser(NsUserTest.JAVAJIGI));

        List<SessionUser> nextStepUsers = sessionRepository.findAllUserBySessionId(savedSession.getId());
        assertThat(nextStepUsers).hasSize(1);
    }

    @Test
    void updateSessionUserStatus() {
        Session savedSession = sessionRepository.save(SessionFixture.createRecruitingSession(), 1L);
        SessionUser sessionUser = new SessionUser(NsUserTest.JAVAJIGI, SessionUserStatus.WAIT);
        sessionRepository.saveSessionUser(savedSession, sessionUser);

        sessionUser.approve();
        sessionRepository.updateSessionUserStatus(savedSession.getId(), sessionUser);

        List<SessionUser> sessionUsers = sessionRepository.findAllUserBySessionId(savedSession.getId());
        assertThat(sessionUsers.get(0)).extracting("sessionUserStatus").isEqualTo(SessionUserStatus.APPROVAL);
    }
}
