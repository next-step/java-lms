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

import static nextstep.courses.domain.session.student.SelectionStatus.APPROVAL;
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

    @DisplayName("수강신청 아이디에 해당하는 학생을 가져온다.")
    @Test
    void findByEnrolmentId() {
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

    @DisplayName("수강생의 선별상태를 수정해 DB에 저장한다.")
    @Test
    void update() {
        // given
        studentRepository.save(new Student(1L, 1L, WAITING));
        Student before = studentRepository.findById(1L)
            .orElseThrow(() -> new IllegalArgumentException("수강생이 존재하지 않습니다."));

        // when
        Student after = before.changeStatus(APPROVAL);
        studentRepository.update(after);

        Student changedStudent = studentRepository.findById(1L)
            .orElseThrow(() -> new IllegalArgumentException("수강생이 존재하지 않습니다."));

        // then
        assertThat(changedStudent.getSelectionStatus()).isEqualTo(APPROVAL);
    }
}