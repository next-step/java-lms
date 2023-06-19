package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.Students;
import nextstep.courses.infrastructure.JdbcCourseRepository;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import nextstep.courses.infrastructure.JdbcSessionUserMappingRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
class SessionServiceTest {
    private SessionService sessionService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final LocalDateTime start = LocalDateTime.of(2023, 6, 28,0,0);;
    private static final LocalDateTime end = LocalDateTime.of(2023, 7, 5,0,0);

    @BeforeEach
    public void setup() {
        JdbcSessionUserMappingRepository sessionUserMappingRepository = new JdbcSessionUserMappingRepository(jdbcTemplate, namedParameterJdbcTemplate);
        JdbcSessionRepository sessionRepository = new JdbcSessionRepository(jdbcTemplate, namedParameterJdbcTemplate);
        JdbcUserRepository userRepository = new JdbcUserRepository(jdbcTemplate, namedParameterJdbcTemplate);
        sessionService = new SessionService(sessionUserMappingRepository, sessionRepository, userRepository);
    }

    @Test
    void findSession() {
        Session session = sessionService.findSession(1L);
        Students students = session.getStudents();
        assertThat(students.getSize()).isEqualTo(2);
    }

    @Test
    void deleteSession() {
        int deleteCount = sessionService.deleteSession(new Session(1L, "16기 java 느리지만 끝까지 하자!", "coby.jpg", start, end));
        assertThat(deleteCount).isEqualTo(1);
    }

    @Test
    void updateSession() {
        Session session1 = new Session(1L, "변경되었나?","coby.jpg", start, end);
        sessionService.updateSession(session1);

        Session session = sessionService.findSession(1L);
        assertThat(session.getTitle()).isEqualTo("변경되었나?");
    }

    @Test
    void insertSession() {
        Session session1 = new Session(50L, "변경되었나?","coby.jpg", start, end);
        sessionService.insertSession(session1);

        Session session = sessionService.findSession(50L);
        assertThat(session.getTitle()).isEqualTo("변경되었나?");
    }
}