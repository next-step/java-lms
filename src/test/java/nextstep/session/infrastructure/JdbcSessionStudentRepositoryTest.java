package nextstep.session.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import nextstep.session.domain.SessionStudentRepository;
import nextstep.session.domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class JdbcSessionStudentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionStudentRepository sessionStudentRepository;

    @BeforeEach
    void setUp() {
        sessionStudentRepository = new JdbcSessionStudentRepository(jdbcTemplate);
    }

    @Test
    void 저장() {

        Student student = new Student(1L, 1L);

        int count = sessionStudentRepository.save(student);
        assertThat(count).isEqualTo(1);
    }

    @Test
    void 수강중인_학생_목록_조회() {
        Student student1 = new Student(1L, 2L);
        Student student2 = new Student(2L, 2L);
        sessionStudentRepository.save(student1);
        sessionStudentRepository.save(student2);
        List<Student> students = sessionStudentRepository.findAllEnrolledInSession(2L);
        assertThat(students.size()).isEqualTo(2);
    }
}
