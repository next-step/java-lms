package nextstep.courses.infrastructure;

import nextstep.courses.domain.model.*;
import nextstep.courses.domain.repository.CourseRepository;
import nextstep.courses.domain.repository.SessionRepository;
import nextstep.users.domain.NsUserTest;
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
class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Course course = courseRepository.findById(1L);

        LocalDateTime now = LocalDateTime.now();
        Session session = Session.createFreeSession(
                course,
                now.plusMonths(1),
                now.plusMonths(2),
                new SessionImage("/image/clean_code.jpg", 300, 200, new byte[300 * 200]),
                ProgressStatus.ACTIVE,
                RegistrationStatus.OPEN, NsUserTest.JAVAJIGI
        );
        long id = sessionRepository.save(session);
        assertThat(id).isEqualTo(session.getId());

        Session saved = sessionRepository.findById(2L);
        assertThat(saved.getCourse()).isEqualTo(course);
        assertThat(saved.getPrice()).isEqualTo(session.getPrice());
        assertThat(saved.getStatus()).isEqualTo(session.getStatus());
        assertThat(saved.getRecruitmentStatus()).isEqualTo(session.getRecruitmentStatus());
        assertThat(saved.getCapacity()).isEqualTo(session.getCapacity());
        assertThat(saved.getImages().size()).isEqualTo(session.getImages().size());
        LOGGER.debug("Session: {}", saved);
    }

}
