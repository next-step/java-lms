package nextstep.courses.infrastructure;

import nextstep.courses.domain.registration.Student;
import nextstep.courses.domain.registration.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static nextstep.courses.domain.registration.StudentMother.aStudent;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class StudentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcStudentRepository(jdbcTemplate);
    }

    @DisplayName("Student 저장")
    @Test
    void save() {
        studentRepository.save(aStudent().build());
        List<Student> students = studentRepository.findAllBySessionId(aStudent().build().getSessionId());
        assertThat(students).hasSize(1);
    }
}
