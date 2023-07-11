package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
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

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcStudentRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Student student = new Student(1L, 1L);
        int count = studentRepository.save(student);
        assertThat(count).isEqualTo(1);
        Student save = studentRepository.findById(1L);

        assertThat(save.getId()).isEqualTo(student.getId());
        assertThat(save.getUserId()).isEqualTo(1L);
        assertThat(save.getSessionId()).isEqualTo(1L);
    }

    @Test
    void findBySessionId() {
        Student student_1 = new Student(1L, 1L);
        Student student_2 = new Student(1L, 2L);
        studentRepository.save(student_1);
        studentRepository.save(student_2);

        List<Student> students = studentRepository.findBySessionId(1L);

        assertThat(students).hasSize(2);
    }
}
