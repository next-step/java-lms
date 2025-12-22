package nextstep.courses.domain.course.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.Sessions;
import nextstep.courses.domain.image.SessionImage;
import nextstep.courses.infrastructure.JdbcCourseRepository;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import nextstep.courses.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class CourseServiceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CourseService courseService;

    @BeforeEach
    void setUp() {
        CourseRepository courseRepository = new JdbcCourseRepository(jdbcTemplate);
        SessionRepository sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        courseService = new CourseService(courseRepository, sessionRepository);
    }

    @Test
    void 같은_이미지를_사용하는_세션들은_이미지를_재사용한다() {
        Course course = createCourseTestFixture();

        courseService.save(course);

        Integer imageCount = jdbcTemplate.queryForObject("select count(*) from session_image", Integer.class);
        assertThat(imageCount).isEqualTo(1);
    }

    private static Course createCourseTestFixture() {
        LocalDate startDate = LocalDate.of(2025, 11, 3);
        LocalDate endDate = LocalDate.of(2025, 12, 18);
        SessionImage image = new SessionImage(300_000L, "png", 600, 400);

        Session session1 = new Session(startDate, endDate, image);
        Session session2 = new Session(startDate, endDate, image);

        Sessions sessions = new Sessions(List.of(session1, session2));
        return new Course("TDD, 클린 코드 with Java", 1L, sessions);
    }
}
