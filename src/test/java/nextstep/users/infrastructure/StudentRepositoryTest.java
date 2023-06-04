package nextstep.users.infrastructure;

import nextstep.users.domain.Student;
import nextstep.users.domain.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

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

    @Test
    @DisplayName("save and select")
    void crud() {
        Student student = new Student(1L, 1L);
        int count = studentRepository.save(student);
        assertThat(count).isEqualTo(1);

        Student savedStudent = studentRepository.findById(1L);
        assertThat(savedStudent).isEqualTo(student);
    }
}
