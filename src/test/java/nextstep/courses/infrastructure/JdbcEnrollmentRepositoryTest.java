package nextstep.courses.infrastructure;

import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.EnrollmentRepository;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionFee;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionState;
import nextstep.courses.infrastructure.image.JdbcImageRepository;
import nextstep.courses.infrastructure.session.JdbcSessionRepository;
import nextstep.courses.mock.MockCourse;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
public class JdbcEnrollmentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private EnrollmentRepository enrollmentRepository;
    private SessionRepository sessionRepository;
    private CourseRepository courseRepository;
    private ImageRepository imageRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        imageRepository = new JdbcImageRepository(jdbcTemplate);
        userRepository = new JdbcUserRepository(jdbcTemplate);
    }

    @Test
    void saveAndFindTest() {
        // given
        long courseId = courseRepository.save(MockCourse.getMockCourse());
        long imageId = imageRepository.save(MockCourse.getMockCoverImage());
        CoverImage coverImage = imageRepository.findById(imageId).get();

        Session session = new Session("sessionTitle",
                                      MockCourse.getMockSessionPeriod(),
                                      SessionState.PREPARING,
                                      coverImage,
                                      SessionFee.ZERO(),
                                      null);
        long sessionId = sessionRepository.save(courseId, session);
        NsUser nsUser = userRepository.findByUserId(JAVAJIGI.getUserId()).get();

        // when
        Enrollment enrollment = new Enrollment(nsUser.getId(), sessionId);
        long enrollmentId = enrollmentRepository.save(enrollment);
        Enrollment enrollmentResult = enrollmentRepository.findById(enrollmentId).get();

        // then
        assertThat(enrollmentResult.nsUserId()).isEqualTo(1L);
        assertThat(enrollmentResult.sessionId()).isEqualTo(sessionId);
    }
}
