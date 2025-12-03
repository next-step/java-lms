package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.session.Enrollment;
import nextstep.courses.domain.session.FreeSessionType;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.Sessions;
import nextstep.courses.domain.session.image.SessionImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class CourseRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        Long courseId = courseRepository.save(course);
        assertThat(courseId).isGreaterThan(0L); // TODO 다른테스트와 시퀀스가 충돌나서 임시 조치
        Course savedCourse = courseRepository.findById(courseId);
        assertThat(course.getTitle()).isEqualTo(savedCourse.getTitle());
        LOGGER.debug("Course: {}", savedCourse);
    }

    @Test
    void sessions를_가진_course_저장하고_조회한다() {
        LocalDate startDate = LocalDate.of(2025, 11, 3);
        LocalDate endDate = LocalDate.of(2025, 12, 18);
        SessionImage image = new SessionImage(300_000L, "png", 600, 400);

        Session session1 = new Session(1, new SessionPeriod(startDate, endDate), image, new Enrollment(SessionStatus.RECRUITING, new FreeSessionType()));
        Session session2 = new Session(2, new SessionPeriod(startDate, endDate), image, new Enrollment(SessionStatus.RECRUITING, new FreeSessionType()));

        Sessions sessions = new Sessions(new ArrayList<>(List.of(session1, session2)));
        Course course = new Course("TDD, 클린 코드 with Java", 1L, sessions);

        Long savedCourseId = courseRepository.save(course);

        Course savedCourse = courseRepository.findById(savedCourseId);
        assertThat(savedCourse.getTitle()).isEqualTo("TDD, 클린 코드 with Java");
        assertThat(savedCourse.getSessions()).isNotNull();
        assertThat(savedCourse.getSessions().size()).isEqualTo(2);
    }

    @Test
    void 같은_이미지를_사용하는_세션들은_이미지를_재사용한다() {
        LocalDate startDate = LocalDate.of(2025, 11, 3);
        LocalDate endDate = LocalDate.of(2025, 12, 18);
        SessionImage image = new SessionImage(500_000L, "png", 600, 400);

        Session session1 = new Session(1, startDate, endDate, image, new Enrollment(SessionStatus.RECRUITING, new FreeSessionType()));
        Session session2 = new Session(2, startDate, endDate, image, new Enrollment(SessionStatus.RECRUITING, new FreeSessionType()));

        Sessions sessions = new Sessions(new ArrayList<>(List.of(session1, session2)));
        Course course = new Course("TDD, 클린 코드 with Java", 1L, sessions);

        courseRepository.save(course);

        Integer imageCount = jdbcTemplate.queryForObject("select count(*) from session_image", Integer.class);
        assertThat(imageCount).isEqualTo(1);
    }
}
