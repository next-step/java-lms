package nextstep.courses.infrastructure;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.EnrollmentRepository;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
        UserRepository userRepository = new JdbcUserRepository(jdbcTemplate);
        NsUser javajigi = userRepository.findByUserId("javajigi").orElseThrow();

        Enrollment enrollment = new Enrollment(1L, javajigi);
        int count = enrollmentRepository.save(enrollment);
        assertThat(count).isEqualTo(1);
        Enrollment savedEnrollment = enrollmentRepository.findById(1L, "javajigi");
        assertThat(savedEnrollment.getStudent()).isEqualTo(enrollment.getStudent());
        assertThat(savedEnrollment.getSessionId()).isEqualTo(enrollment.getSessionId());
        assertThat(savedEnrollment.getEnrollDate()).isEqualTo(enrollment.getEnrollDate());
        assertThat(savedEnrollment.getApprovalState()).isEqualTo(enrollment.getApprovalState());
    }
}
