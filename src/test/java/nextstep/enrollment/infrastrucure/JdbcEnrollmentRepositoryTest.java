package nextstep.enrollment.infrastrucure;

import static nextstep.enrollment.domain.EnrollmentStatus.APPROVED;
import static nextstep.enrollment.domain.EnrollmentStatus.REJECTED;
import static nextstep.sessions.domain.SessionProgressStatus.PREPARING;
import static nextstep.sessions.domain.SessionRecruitingStatus.RECRUITING;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static nextstep.users.domain.NsUserType.WOOTECO;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import nextstep.enrollment.domain.Enrollment;
import nextstep.enrollment.domain.EnrollmentRepository;
import nextstep.enrollment.domain.EnrollmentStatus;
import nextstep.sessions.domain.Session;
import nextstep.users.domain.NsUser;

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
    void 수강_신청을_승인한다() {
        // given
        final NsUser user = new NsUser(3L, "lee", "password", "name", "lee@slipp.net", WOOTECO);
        final Session session = new Session(1L, 30, RECRUITING, PREPARING, 800_000, LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusMonths(1), null, JAVAJIGI.getId());
        final Enrollment enrollment = new Enrollment(2L, session, user);
        enrollment.enroll(800_000);

        // when
        enrollment.approveBy(JAVAJIGI);
        enrollmentRepository.save(enrollment);

        // then
        assertSoftly(
                softly -> {
                    softly.assertThat(findEnrollmentStatus(enrollment.getId())).isEqualTo(APPROVED);
                    softly.assertThat(session.getEnrollments()).hasSize(1)
                            .extracting("attendee")
                            .containsExactly(user);
                }
        );
    }

    @Test
    void 수강_신청을_거절한다() {
        // given
        final Session session = new Session(1L, 30, RECRUITING, PREPARING, 800_000, LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusMonths(1), null, JAVAJIGI.getId());
        final Enrollment enrollment = new Enrollment(1L, session, SANJIGI);
        enrollment.enroll(800_000);

        // when
        enrollment.cancelBy(JAVAJIGI);
        enrollmentRepository.save(enrollment);

        // then
        assertSoftly(
                softly -> {
                    softly.assertThat(findEnrollmentStatus(enrollment.getId())).isEqualTo(REJECTED);
                    softly.assertThat(session.getEnrollments()).hasSize(0);
                }
        );
    }

    private EnrollmentStatus findEnrollmentStatus(final Long enrollmentId) {
        final String sql = "select enrollment_status from enrollment where id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> EnrollmentStatus.valueOf(rs.getString("enrollment_status")), enrollmentId);
    }
}
