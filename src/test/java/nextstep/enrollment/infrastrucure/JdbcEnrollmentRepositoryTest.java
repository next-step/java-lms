package nextstep.enrollment.infrastrucure;

import static nextstep.sessions.domain.SessionRecruitingStatus.RECRUITING;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import nextstep.enrollment.domain.Enrollment;
import nextstep.enrollment.domain.EnrollmentRepository;
import nextstep.sessions.domain.Session;
import nextstep.users.domain.NsUserTest;

@JdbcTest
class JdbcEnrollmentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private EnrollmentRepository enrollmentRepository;

    @BeforeEach
    void setUp() {
        enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate);
    }

    @Test
    void 등록을_저장한다() {
        // given
        final Session session = new Session(1L, 30, RECRUITING, 800_000, LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusMonths(1), null);
        final Enrollment enrollment = new Enrollment(session, NsUserTest.SANJIGI);

        // when
        final int count = enrollmentRepository.save(enrollment);

        // then
        assertThat(count).isEqualTo(1);
    }
}
