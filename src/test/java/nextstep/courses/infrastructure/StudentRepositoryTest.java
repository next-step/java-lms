package nextstep.courses.infrastructure;

import nextstep.courses.domain.registration.Student;
import nextstep.courses.domain.registration.StudentRepository;
import org.assertj.core.api.Assertions;
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
        studentRepository.save(aStudent().build());
    }

    @DisplayName("Student 저장")
    @Test
    void save() {
        List<Student> students = studentRepository.findAllBySessionId(aStudent().build().getSessionId());
        assertThat(students).hasSize(1);
    }

    @DisplayName("Student 상태 업데이트")
    @Test
    void updateStatus() {
        studentRepository.updateStatus(aStudent()
                .withStatus(false)
                .build());

        Student student = studentRepository.findByUserId(aStudent().build().getNsUserId());
        Assertions.assertThat(student.getStatus()).isFalse();
    }
}
