package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.image.CoverImage;
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

import static nextstep.courses.infrastructure.TestUtil.autoincrementReset;
import static nextstep.courses.infrastructure.TestUtil.createDefaultTddSession;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class ImageRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ImageRepository imageRepository;
    private CourseRepository courseRepository;
    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        autoincrementReset(jdbcTemplate);
        imageRepository = new JdbcImageRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        courseRepository.save(course);
        Course savedCourse = courseRepository.findById(1L).get();

        final Session tddSession = createDefaultTddSession();
        sessionRepository.save(savedCourse.id(), tddSession);

        CoverImage savedImage = imageRepository.findById(1L).get();
        assertThat(savedImage.size()).isEqualTo(1024L);
        assertThat(savedImage.imagePixel().width()).isEqualTo(300);
        assertThat(savedImage.imagePixel().height()).isEqualTo(200);
        assertThat(savedImage.imageType()).isEqualTo(ImageType.GIF);
    }
}
