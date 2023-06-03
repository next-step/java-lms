package nextstep.courses.infrastructure;

import nextstep.courses.domain.StudentRepository;
import nextstep.courses.domain.enrollment.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@JdbcTest
public class StudentRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcStudentRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("create 와 read 기능을 올바르게 수행할 수 있다.")
    void dbTest_Create_Read() {
        Student student = new Student(1L, 1L);
        int count = studentRepository.save(student);
        Assertions.assertThat(count).isEqualTo(1);

        Student savedStudent = studentRepository.findById(1L);
        Assertions.assertThat(student).isEqualTo(savedStudent);

//        List<Student> students = studentRepository.findBySessionId(1L);
//        Assertions.assertThat(students).contains(student);
    }

}
