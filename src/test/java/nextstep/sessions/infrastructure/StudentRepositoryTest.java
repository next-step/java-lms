package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.Student;
import nextstep.sessions.domain.StudentRepository;
import nextstep.sessions.domain.builder.SessionBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class StudentRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    SessionRepository sessionRepository;

    StudentRepository studentRepository;

    Student student = new Student(1L, 1L);

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        studentRepository = new JdbcStudentRepository(jdbcTemplate);

        Session tddWithJava = new SessionBuilder()
                .withSessionName("tdd with java")
                .build();
        sessionRepository.save(tddWithJava);
    }

    @Test
    void save() {

        int count = studentRepository.save(student);

        assertThat(count).isEqualTo(1);
    }

    @Test
    void findBySessionId() {
        studentRepository.save(new Student(1L, 1L));

        List<Student> students = studentRepository.findBySessionId(1L);

        Assertions.assertThat(students).hasSize(1);
    }
}
