package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.courses.domain.SessionBuilder.session;
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
        assertThat(save.getUser_id()).isEqualTo(1L);
        assertThat(save.getSession_id()).isEqualTo(1L);
    }
}
