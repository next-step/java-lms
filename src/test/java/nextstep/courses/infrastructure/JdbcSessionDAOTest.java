package nextstep.courses.infrastructure;

import nextstep.courses.domain.EnrollmentStatus;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.session.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static nextstep.courses.domain.SessionTest.FREE_SESSION_1_UPDATED;
import static nextstep.courses.domain.SessionTest.FREE_SESSION_1;
import static nextstep.courses.domain.StudentTest.STUDENT_1;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcSessionDAOTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionDAO.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SessionDAO sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionDAO(jdbcTemplate);
    }

    @Test
    void session_save_find() {
        Long sessionId = sessionRepository.save(FREE_SESSION_1);
        assertThat(sessionId).isEqualTo(1L);
        Session savedSession = sessionRepository.findById(sessionId);
        assertThat(FREE_SESSION_1).isEqualTo(savedSession);
        LOGGER.debug("Session: {}", savedSession);

        sessionRepository.updateSessionUserNumber(FREE_SESSION_1_UPDATED);
        Session updatedSession = sessionRepository.findById(sessionId);
        assertThat(FREE_SESSION_1_UPDATED).isEqualTo(updatedSession);
        LOGGER.debug("Updated Session: {}", savedSession);
    }

    @Test
    void student_save_find() {
        int count = sessionRepository.saveStudent(STUDENT_1);
        assertThat(count).isEqualTo(1);
        List<Student> nsUserSessions = sessionRepository.findStudentsBySessionId(1L);
        assertThat(nsUserSessions.get(0)).isEqualTo(STUDENT_1);

        Student NS_USER_SESSION_1_APPROVED = new Student(1L, 1L, EnrollmentStatus.APPROVED);
        sessionRepository.updateStudent(NS_USER_SESSION_1_APPROVED);
        List<Student> updatedNsUserSessions = sessionRepository.findStudentsBySessionId(1L);
        assertThat(updatedNsUserSessions.get(0)).isEqualTo(NS_USER_SESSION_1_APPROVED);
    }
}
