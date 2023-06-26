package nextstep.student.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.Student;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import nextstep.courses.infrastructure.JdbcStudentRepository;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@JdbcTest
public class StudentRepositoryTest {

    private JdbcStudentRepository jdbcStudentRepository;
    private JdbcSessionRepository jdbcSessionRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcStudentRepository = new JdbcStudentRepository(jdbcTemplate);
        jdbcSessionRepository = new JdbcSessionRepository(jdbcTemplate, jdbcStudentRepository);
    }

    @Test
    @DisplayName("CRUD 테스트")
    void studentCrudTest() {
        Session session = jdbcSessionRepository.findById(1L);

        Student student = new Student(
                session,
                NsUserTest.JAVAJIGI
        );

        int cnt = jdbcStudentRepository.save(student);
        List<Student> students = jdbcStudentRepository.findBySessionId(session.getId());

        Assertions.assertThat(cnt).isEqualTo(1);
        Assertions.assertThat(students).hasSize(1);
    }
}
