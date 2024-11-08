package nextstep.courses.infrastructure;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.courses.domain.cover.ImageDimension;
import nextstep.courses.domain.cover.ImageExtension;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.courses.domain.session.*;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
class EnrollmentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private EnrollmentRepository enrollmentRepository;

    private UserRepository userRepository;

    private SessionRepository sessionRepository;

    private Long sessionId;

    @BeforeEach
    void setUp() {
        userRepository = new JdbcUserRepository(jdbcTemplate);
        enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate, userRepository);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate, enrollmentRepository);

        CoverImage coverImage = CoverImage.of("file.jpg", ImageSize.of(1000), ImageExtension.JPG.name(), ImageDimension.of(300, 200));
        SessionPeriod period = SessionPeriod.of(LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        Session session = new FreeSession(1L, 1L, SessionBody.of("테스트 세션", period, coverImage), SessionEnrollment.of(SessionStatus.OPEN, Set.of()));
        sessionRepository.save(session);
        sessionId = session.getId();
    }

    @DisplayName("사용자를 수강신청하고 수강신청된 사용자들을 조회할 수 있다.")
    @Test
    void enrollAndFindEnrolledUsersBySessionId() {
        enrollmentRepository.save(sessionId, NsUserTest.JAVAJIGI);

        Set<NsUser> enrolledUsers = enrollmentRepository.findEnrolledUsersBySessionId(sessionId);
        assertThat(enrolledUsers).hasSize(1);

        NsUser enrolledUser = enrolledUsers.iterator().next();
        assertAll(
                () -> assertThat(enrolledUser.getId()).isEqualTo(NsUserTest.JAVAJIGI.getId()),
                () -> assertThat(enrolledUser.getUserId()).isEqualTo(NsUserTest.JAVAJIGI.getUserId()),
                () -> assertThat(enrolledUser.getPassword()).isEqualTo(NsUserTest.JAVAJIGI.getPassword()),
                () -> assertThat(enrolledUser.getName()).isEqualTo(NsUserTest.JAVAJIGI.getName()),
                () -> assertThat(enrolledUser.getEmail()).isEqualTo(NsUserTest.JAVAJIGI.getEmail())
        );
    }

}