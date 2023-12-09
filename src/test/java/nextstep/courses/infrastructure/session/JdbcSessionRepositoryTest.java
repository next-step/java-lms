package nextstep.courses.infrastructure.session;

import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.session.EnrollmentCount;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionFee;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionState;
import nextstep.courses.infrastructure.JdbcCourseRepository;
import nextstep.courses.infrastructure.image.JdbcImageRepository;
import nextstep.courses.mock.MockCourse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
class JdbcSessionRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    private CourseRepository courseRepository;
    private ImageRepository imageRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        imageRepository = new JdbcImageRepository(jdbcTemplate);
    }

    @Test
    void saveAndFindTest() {
        // given
        long courseId = courseRepository.save(MockCourse.getMockCourse());
        long imageId = imageRepository.save(MockCourse.getMockCoverImage());
        CoverImage coverImage = imageRepository.findById(imageId).get();

        // when
        Session session = new Session("sessionTitle", MockCourse.getMockSessionPeriod(), SessionState.PREPARING, coverImage, SessionFee.of(100L), new EnrollmentCount(20));
        long sessionId = sessionRepository.save(courseId, session);
        Session sessionResult = sessionRepository.findById(sessionId).get();
        // then
        assertThat(sessionResult.getTitle()).isEqualTo("sessionTitle");
        assertThat(sessionResult.getSessionState()).isEqualTo(SessionState.PREPARING);
        assertThat(sessionResult.getSessionFee()).isEqualTo(SessionFee.of(100L));
    }
}
