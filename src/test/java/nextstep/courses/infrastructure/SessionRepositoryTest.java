package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.session.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Rollback(value = false)
@JdbcTest
public class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    private CourseRepository courseRepository;

    private Session session;

    @BeforeEach
    void setUp() {
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate, courseRepository);
        Course course = new Course(1L, "TDD, 클린 코드 with Java", 1L, LocalDateTime.now(), null);
        courseRepository.save(course);

        session = new Session(1L, course, new SessionImage("a", "/"),
                new SessionCapacity(0, 10), "TDD, 클린코드", SessionType.PAY,
                SessionStatus.READY, RecruitmentStatus.OPEN, LocalDateTime.now(), null);
    }

    @Test
    void 강의를_생성한다() {
        // given & when
        int count = sessionRepository.save(session);

        // then
        assertThat(count).isEqualTo(1);
    }

    @Test
    void 강의를_강의ID로_검색한다() {
        // given & when
        Session savedSession = sessionRepository.findById(1L);

        // then
        assertThat(session.getName()).isEqualTo(savedSession.getName());
    }

    @Test
    void 강의를_갱신한다() {
        // given
        session.enroll();

        // when
        sessionRepository.update(session);

        // then
        assertThat(session.getCapacity().getNumber()).isEqualTo(1);
    }
}
