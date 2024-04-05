package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class CourseRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CourseRepository courseRepository;
    Course course;

    @BeforeEach
    void setUp() {
        courseRepository = new JdbcCourseRepository(jdbcTemplate);

        Session.Period period = new Session.Period(LocalDate.of(2024, 2, 4), LocalDate.of(2024, 10, 9));
        Image image = new Image(1000, 200, 300, "test.jpg");
        NsUsers nsUsers = NsUsers.from(new ArrayList<>());
        List<Session> sessions = new ArrayList<>();
        sessions.add(new FreeSession(1L, null, period, image, Session.Status.RECRUITING, nsUsers));

        course = new Course(1L, "무료테스트", 1L, new Sessions(sessions), LocalDateTime.of(2024, 1, 10, 1, 1, 1), LocalDateTime.of(2024, 1, 10, 1, 1, 1));
    }

    @Test
    void crud() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        int count = courseRepository.save(course);
        assertThat(count).isEqualTo(1);
        Course savedCourse = courseRepository.findById(1L);
        assertThat(course.getTitle()).isEqualTo(savedCourse.getTitle());
        LOGGER.debug("Course: {}", savedCourse);
    }

    @DisplayName("해당 강의가 무료인지 확인할 수 있다.")
    @Test
    void isFreeSession() {

        Session.Period period = new Session.Period(LocalDate.of(2024, 2, 4), LocalDate.of(2024, 10, 9));
        Image image = new Image(1000, 200, 300, "test.jpg");
        NsUsers nsUsers = NsUsers.from(new ArrayList<>());
        Course course = new Course(1L, "무료테스트", 1L, new Sessions(List.of(new FreeSession(1L, null, period, image, Session.Status.RECRUITING, nsUsers))), LocalDateTime.of(2024, 1, 10, 1, 1, 1), LocalDateTime.of(2024, 1, 10, 1, 1, 1));

        Assertions.assertThat(course.isFreeSession(1L)).isTrue();
    }

    @DisplayName("무료 강의를 등록할 수 있다.")
    @Test
    void enroll() {
        Assertions.assertThatCode(() -> course.enroll(new NsUser(), 1L))
                .doesNotThrowAnyException();
    }

    @DisplayName("유료 강의를 등록할 수 있다.")
    @Test
    void enrollWithPaidSession() {
        Session.Period period = new Session.Period(LocalDate.of(2024, 2, 4), LocalDate.of(2024, 10, 9));
        Image image = new Image(1000, 200, 300, "test.jpg");
        NsUsers nsUsers = NsUsers.from(new ArrayList<>());

        PaidSession paidSession = new PaidSession(2L, null, period, image, Session.Status.RECRUITING, nsUsers, 5, 5000L);
        course.addSession(paidSession);
        Assertions.assertThatCode(() -> course.enroll(new NsUser(), 2L))
                .doesNotThrowAnyException();
    }
}
