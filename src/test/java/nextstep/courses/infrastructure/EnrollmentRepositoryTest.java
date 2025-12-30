package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class EnrollmentRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private EnrollmentRepository enrollmentRepository;

    @BeforeEach
    void setUp() {
        enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Enrollment enrollment = new Enrollment(1L, 1L);
        int count = enrollmentRepository.save(enrollment);
        assertThat(count).isEqualTo(1);
        Enrollment savedEnrollment = enrollmentRepository.findById(1L);
        assertThat(enrollment.studentId()).isEqualTo(savedEnrollment.studentId());
        assertThat(enrollment.sessionId()).isEqualTo(savedEnrollment.sessionId());
        LOGGER.debug("Enrollment: {}", savedEnrollment);
    }

    @Test
    void 세션_아이디로_수강신청_리스트_조회에_성공한다() {
        Long sessionId = 1L;
        enrollmentRepository.save(new Enrollment(1L, sessionId));
        enrollmentRepository.save(new Enrollment(2L, sessionId));
        enrollmentRepository.save(new Enrollment(999L, 999L));

        List<Enrollment> enrollments = enrollmentRepository.findBySessionId(sessionId);

        assertThat(enrollments.size()).isEqualTo(2);
        assertThat(enrollments)
                .extracting(Enrollment::studentId)
                .containsExactlyInAnyOrder(1L, 2L);
        assertThat(enrollments)
                .extracting(Enrollment::sessionId)
                .containsOnly(sessionId);
    }
}
