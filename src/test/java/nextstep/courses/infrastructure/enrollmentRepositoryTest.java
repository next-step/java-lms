package nextstep.courses.infrastructure;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentRepository;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImagePixel;
import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.image.ImageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class enrollmentRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(enrollmentRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private EnrollmentRepository enrollmentRepository;

    @BeforeEach
    void setUp() {
        autoincrementReset();
        enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate);
    }

    @Test
    void crud() {
//        final Enrollment enrollment = new Enrollment(1L, 1L);
//        int count = enrollmentRepository.save(enrollment);
//        assertThat(count).isEqualTo(1);
//        Enrollment savedEnrollment = enrollmentRepository.findById(1L);
//        assertThat(savedEnrollment.nsUserId()).isEqualTo(5L);
//        assertThat(savedEnrollment.sessionId()).isEqualTo(10L);
    }

    private void autoincrementReset() {
        jdbcTemplate.execute("ALTER TABLE course ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE image ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE session ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE enrollment ALTER COLUMN id RESTART WITH 1");
    }
}
