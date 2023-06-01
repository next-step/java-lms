package nextstep.infrastructure;

import nextstep.sessions.domain.*;
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
class StudentRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionStudentRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionStudentRepository sessionStudentRepository;

    @BeforeEach
    void setUp() {
        sessionStudentRepository = new JdbcStudentRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        sessionStudentRepository.takeSession(1L, 1L);
        sessionStudentRepository.takeSession(1L, 2L);
        List<SessionStudent> students = sessionStudentRepository.getStudents(1L);
        assertThat(students.size()).isEqualTo(2);
        LOGGER.debug("students: {}", students);
    }

}
