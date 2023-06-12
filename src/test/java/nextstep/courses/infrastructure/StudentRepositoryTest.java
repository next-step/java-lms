package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.SessionRegisterStatus;
import nextstep.courses.domain.session.Student;
import nextstep.courses.repository.StudentRepository;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcStudentRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        List<Student> students1 = studentRepository.findBySessionId(0L);
        assertThat(students1).hasSize(1);
        assertThat(students1).containsExactlyElementsOf(List.of(
                new Student(0L, 1L, SessionRegisterStatus.APPROVED)));

        studentRepository.registerSession(0L, 2L);
        List<Student> students2 = studentRepository.findBySessionId(0L);
        assertThat(students2).hasSize(2);
        assertThat(students2).containsExactlyElementsOf(List.of(
                new Student(0L, 1L, SessionRegisterStatus.APPROVED),
                new Student(0L, 2L, SessionRegisterStatus.WAITING)));
    }

    @Test
    void rejectAndApprove() {
        List<Student> students1 = studentRepository.findBySessionId(0L);
        assertThat(students1).hasSize(1);
        assertThat(students1.get(0).getSessionRegisterStatus()).isEqualTo(SessionRegisterStatus.APPROVED);

        studentRepository.reject(new Student(0L, 1L));
        List<Student> students2 = studentRepository.findBySessionId(0L);
        assertThat(students2.get(0).getSessionRegisterStatus()).isEqualTo(SessionRegisterStatus.REJECTED);

        studentRepository.approve(new Student(0L, 1L));
        List<Student> students3 = studentRepository.findBySessionId(0L);
        assertThat(students3.get(0).getSessionRegisterStatus()).isEqualTo(SessionRegisterStatus.APPROVED);
    }
}
