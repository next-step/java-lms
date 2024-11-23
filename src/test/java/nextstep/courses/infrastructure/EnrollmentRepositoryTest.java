package nextstep.courses.infrastructure;

import nextstep.courses.domain.cover.*;
import nextstep.courses.domain.session.*;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
class EnrollmentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private EnrollmentRepository enrollmentRepository;
    private CoverImageRepository coverImageRepository;

    private SessionRepository sessionRepository;

    private Long sessionId;

    @BeforeEach
    void setUp() {
        enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate);
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate, enrollmentRepository, coverImageRepository);

        CoverImage coverImage = CoverImage.of("file.jpg", ImageSize.of(1000), ImageExtension.JPG.name(), ImageDimension.of(300, 200));
        SessionPeriod period = SessionPeriod.of(LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        Session session = new FreeSession(
                1L, 1L, SessionBody.of("테스트 세션", period, List.of(coverImage)),
                SessionEnrollment.of(ProgressStatus.IN_PROGRESS, RecruitmentStatus.NOT_RECRUITING, EnrolledUsers.of(new HashSet<>()))
        );
        sessionRepository.save(session);
        sessionId = session.getId();
    }

    @DisplayName("사용자를 수강신청하고 수강신청된 사용자들을 조회할 수 있다.")
    @Test
    void enrollAndFindEnrolledUsersBySessionId() {
        enrollmentRepository.save(sessionId, NsUserTest.JAVAJIGI);
        NsUser enrolledUser = getSingleEnrolledUser();

        assertThat(enrolledUser)
                .usingRecursiveComparison()
                .ignoringFields("createdAt", "updatedAt")
                .isEqualTo(NsUserTest.JAVAJIGI);
    }

    @DisplayName("수강신청한 사용자의 수강 상태를 변경할 수 있다.")
    @Test
    void updateEnrollmentStatusTest() {
        NsUser user = new NsUser(1L, "pobijigi", "test", "포비지기", "pobijigi@slipp.net", EnrollmentStatus.PENDING, LocalDateTime.now(), LocalDateTime.now());
        enrollmentRepository.save(sessionId, user);

        NsUser enrolledUser = getSingleEnrolledUser();
        assertThat(enrolledUser.getEnrollmentStatus()).isEqualTo(EnrollmentStatus.PENDING);

        enrollmentRepository.updateEnrollmentStatus(sessionId, user.getId(), EnrollmentStatus.APPROVED);

        NsUser approvedUser = getSingleEnrolledUser();
        assertThat(approvedUser.getEnrollmentStatus()).isEqualTo(EnrollmentStatus.APPROVED);

        enrollmentRepository.updateEnrollmentStatus(sessionId, user.getId(), EnrollmentStatus.REJECTED);

        NsUser rejectedUser = getSingleEnrolledUser();
        assertThat(rejectedUser.getEnrollmentStatus()).isEqualTo(EnrollmentStatus.REJECTED);

    }

    private NsUser getSingleEnrolledUser() {
        Set<NsUser> enrolledUsers = enrollmentRepository.findEnrolledUsersBySessionId(sessionId);
        assertThat(enrolledUsers).hasSize(1);
        return enrolledUsers.iterator().next();
    }

}