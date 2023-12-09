package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImagePixel;
import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.image.ImageType;
import nextstep.courses.domain.session.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SessionRepositoryTest extends TestUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    private CourseRepository courseRepository;
    private ImageRepository imageRepository;

    @BeforeEach
    void setUp() {
        autoincrementReset(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        imageRepository = new JdbcImageRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        courseRepository.save(course);

        final CoverImage coverImage = new CoverImage(1024L, new ImagePixel(300, 200), ImageType.GIF);
        imageRepository.save(coverImage);
        CoverImage savedCoverImage = imageRepository.findById(1L).get();

        final Amount amount = Amount.of(100L);
        SessionPeriod sessionPeriod = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        final Session tddSession = new Session("tdd", sessionPeriod, SessionStatus.PREPARING, savedCoverImage, amount, new EnrollmentCount(20));
        int count = sessionRepository.save(1L, tddSession);
        assertThat(count).isEqualTo(1);

        Session savedTddSession = sessionRepository.findById(1L).get();
        assertThat(savedTddSession.title()).isEqualTo("tdd");
        assertThat(savedTddSession.sessionStatus()).isEqualTo(SessionStatus.PREPARING);
        assertThat(savedTddSession.amount()).isEqualTo(Amount.of(100L));
    }
}