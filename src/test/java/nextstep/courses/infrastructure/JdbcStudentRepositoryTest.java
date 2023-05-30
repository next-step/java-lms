package nextstep.courses.infrastructure;

import nextstep.courses.domain.Student;
import nextstep.courses.domain.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcStudentRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcStudentRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcStudentRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Student student = new Student(1, 1L);
        int count = studentRepository.save(student);
        assertThat(count).isEqualTo(1);

        Student savedStudent = studentRepository.findById(1, 1L);
        assertThat(savedStudent.getSessionId()).isEqualTo(student.getSessionId());
    }
}