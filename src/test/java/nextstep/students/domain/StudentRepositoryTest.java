package nextstep.students.domain;

import nextstep.session.domain.Session;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.SessionStatus;
import nextstep.session.infrastructure.JdbcSessionRepository;
import nextstep.students.infrastructure.JdbcStudentRepository;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class StudentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentRepository studentRepository;

    private UserRepository userRepository;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcStudentRepository(jdbcTemplate);
        userRepository = new JdbcUserRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Students students = new Students(2L, 1L);
        int count = studentRepository.save(students);
        Assertions.assertThat(count).isEqualTo(1);
    }
}
