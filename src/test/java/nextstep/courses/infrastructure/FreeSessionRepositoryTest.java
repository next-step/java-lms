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
class FreeSessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private FreeSessionRepository freeSessionRepository;

    private CourseRepository courseRepository;
    private ImageRepository imageRepository;

    @BeforeEach
    void setUp() {
        autoincrementReset();
        freeSessionRepository = new JdbcFreeSessionRepository(jdbcTemplate);
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

        SessionPeriod sessionPeriod = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        final FreeSession tddSession = new FreeSession("tdd", sessionPeriod, SessionStatus.PREPARING, savedCoverImage);
        int count = freeSessionRepository.save(1L, tddSession);
        assertThat(count).isEqualTo(1);

        FreeSession savedTddSession = freeSessionRepository.findById(1L).get();
        assertThat(savedTddSession.title()).isEqualTo("tdd");
        assertThat(savedTddSession.sessionStatus()).isEqualTo(SessionStatus.PREPARING);
    }

    private void autoincrementReset() {
        jdbcTemplate.execute("ALTER TABLE course ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE image ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE session ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE enrollment ALTER COLUMN id RESTART WITH 1");
    }
}