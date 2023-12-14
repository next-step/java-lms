package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.enroll.Enrolment;
import nextstep.courses.domain.session.repository.EnrolmentRepository;
import nextstep.courses.domain.session.repository.SessionStudentRepository;
import nextstep.courses.domain.session.student.SessionStudent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.courses.domain.session.student.SelectionStatus.*;
import static org.assertj.core.api.Assertions.*;

@JdbcTest
class JdbcEnrolmentRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private EnrolmentRepository enrolmentRepository;
    private SessionStudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcSessionStudentRepository(jdbcTemplate);
        enrolmentRepository = new JdbcEnrolmentRepository(jdbcTemplate, studentRepository);
    }

    @DisplayName("강의 아이디에 일치하는 수강 신청 데이터를 찾는다.")
    @Test
    void findBySession() {
        studentRepository.save(new SessionStudent(1L, 1L, WAITING));
        studentRepository.save(new SessionStudent(1L, 2L, WAITING));
        studentRepository.save(new SessionStudent(1L, 3L, WAITING));

        Enrolment enrolment = enrolmentRepository.findBySession(1L)
            .orElseThrow();

        assertThat(enrolment.studentsSize()).isEqualTo(3);
    }
}