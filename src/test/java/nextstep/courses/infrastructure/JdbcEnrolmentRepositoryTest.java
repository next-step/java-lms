package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.enroll.Enrolment;
import nextstep.courses.domain.session.repository.EnrolmentRepository;
import nextstep.courses.domain.session.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
class JdbcEnrolmentRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private EnrolmentRepository enrolmentRepository;
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcStudentRepository(jdbcTemplate);
        enrolmentRepository = new JdbcEnrolmentRepository(jdbcTemplate, studentRepository);
    }

    @DisplayName("강의 아이디에 일치하는 수강 신청 데이터를 찾는다.")
    @Test
    void findBySession() {
        Enrolment enrolment = enrolmentRepository.findBySession(1L)
            .orElseThrow();

        assertThat(enrolment.studentsSize()).isZero();
    }
}