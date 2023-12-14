package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.repository.SessionStudentRepository;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.domain.session.student.SessionStudents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.courses.domain.session.enums.SelectionStatus.APPROVAL;
import static nextstep.courses.domain.session.enums.SelectionStatus.WAITING;
import static org.assertj.core.api.Assertions.*;

@JdbcTest
class JdbcSessionStudentRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private SessionStudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcSessionStudentRepository(jdbcTemplate);
    }

    @DisplayName("수강신청 아이디에 해당하는 학생을 가져온다.")
    @Test
    void findByEnrolmentId() {
        // given
        Long sessionId = 1L;
        studentRepository.save(new SessionStudent(sessionId, 1L, WAITING));
        studentRepository.save(new SessionStudent(sessionId, 2L, WAITING));
        studentRepository.save(new SessionStudent(sessionId, 3L, WAITING));

        // when
        SessionStudents allBySession = studentRepository.findAllBySession(sessionId);

        // then
        assertThat(allBySession.numOfAllStudents()).isEqualTo(3);
    }

    @DisplayName("수강생의 선별상태를 수정해 DB에 저장한다.")
    @Test
    void update() {
        // given
        studentRepository.save(new SessionStudent(1L, 1L, WAITING));
        SessionStudent before = studentRepository.findById(1L)
            .orElseThrow(() -> new IllegalArgumentException("수강생이 존재하지 않습니다."));

        // when
        SessionStudent after = before.changeStatus(APPROVAL);
        studentRepository.update(after);

        SessionStudent changedStudent = studentRepository.findById(1L)
            .orElseThrow(() -> new IllegalArgumentException("수강생이 존재하지 않습니다."));

        // then
        assertThat(changedStudent.getSelectionStatus()).isEqualTo(APPROVAL);
    }
}