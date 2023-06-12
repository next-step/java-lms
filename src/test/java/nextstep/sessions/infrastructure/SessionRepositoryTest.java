package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.*;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    private Session session;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        session = new Session(SessionTest.s1, new SessionStudents(1));
    }

    @Test
    void save_and_findById() {
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L).orElseThrow();
        assertThat(savedSession.getCourseId()).isEqualTo(session.getCourseId());
    }

    @Test
    void saveUser_and_findWithStudentById() {
        int count = sessionRepository.enrollUser(1L, NsUserTest.JAVAJIGI);
        assertThat(count).isEqualTo(1);

        sessionRepository.enrollUser(1L, NsUserTest.SANJIGI);

        List<NsUser> allUsers = sessionRepository.findAllUsersBySessionId(1L);

        assertThat(allUsers.size()).isEqualTo(2);
        assertThat(allUsers.get(0).getId()).isEqualTo(1L);
    }

}
