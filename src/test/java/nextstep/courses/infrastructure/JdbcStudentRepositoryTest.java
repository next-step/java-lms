package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.repository.StudentRepository;
import nextstep.courses.domain.session.student.Student;
import nextstep.courses.domain.session.student.Students;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.courses.domain.session.student.SelectionStatus.WAITING;
import static org.assertj.core.api.Assertions.*;

@JdbcTest
class JdbcStudentRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcStudentRepository(jdbcTemplate);
    }
    
    @DisplayName("강의 아이디에 해당하는 학생을 가져온다.")
    @Test
    void findById() {
        // given
        Long enrolmentId = 1L;
        studentRepository.save(new Student(enrolmentId, 1L, WAITING));
        studentRepository.save(new Student(enrolmentId, 2L, WAITING));
        studentRepository.save(new Student(enrolmentId, 3L, WAITING));

        // when
        Students allBySession = studentRepository.findAllByEnrolment(enrolmentId);

        // then
        assertThat(allBySession.size()).isEqualTo(3);
    }
}