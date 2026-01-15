package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import nextstep.courses.domain.EnrollmentRepository;
import nextstep.courses.domain.enrollment.Enrollment;
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
    void 수강신청을_저장할_수_있다() {
        Enrollment enrollment = new Enrollment(1L, 1L);

        Long savedId = enrollmentRepository.save(enrollment);

        assertThat(savedId).isNotNull();
    }

    @Test
    void 강의_ID로_수강신청_목록을_조회할_수_있다() {
        enrollmentRepository.save(new Enrollment(1L, 1L));
        enrollmentRepository.save(new Enrollment(1L, 2L));

        List<Enrollment> enrollments = enrollmentRepository.findBySessionId(1L);

        assertThat(enrollments).hasSize(2);
    }
}
