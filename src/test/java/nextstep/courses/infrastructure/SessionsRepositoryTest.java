package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.*;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
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
public class SessionsRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionsRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionsRepository sessionsRepository;

    private SessionRepository sessionRepository;

    private ImageRepository imageRepository;

    private UserRepository userRepository;

    private StudentsRepository studentsRepository;

    @BeforeEach
    void setUp() {
        imageRepository = new JdbcImageRepository(jdbcTemplate);
        userRepository = new JdbcUserRepository(jdbcTemplate);
        studentsRepository = new JdbcStudentsRepository(jdbcTemplate, userRepository);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate, imageRepository, studentsRepository);
        sessionsRepository = new JdbcSessionsRepository(jdbcTemplate, sessionRepository);
    }

    @Test
    void crud() {
        int count = sessionsRepository.save(1L, Sessions.of(강의_목록_조회()));
        assertThat(count).isEqualTo(2);
        Sessions findSessions = sessionsRepository.findByCourseId(1L);
        assertThat(findSessions.size()).isEqualTo(2);
        LOGGER.debug("findSessions: {}", findSessions);
    }

    private List<Session> 강의_목록_조회() {
        Session findPaidSession = sessionRepository.findById(1000L);
        Session findFreeSession = sessionRepository.findById(2000L);

        return List.of(findPaidSession, findFreeSession);
    }
}
