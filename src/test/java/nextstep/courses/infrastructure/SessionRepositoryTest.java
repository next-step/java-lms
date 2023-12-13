package nextstep.courses.infrastructure;

import nextstep.courses.InvalidImageFormatException;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.image.ImageType;
import nextstep.courses.domain.image.SessionImage;
import nextstep.courses.domain.image.SessionImageRepository;
import nextstep.courses.domain.session.EnrollmentStatus;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionImageRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("강의 정보를 저장한다.")
    void save_강의_Test() throws InvalidImageFormatException {
        Course course = new Course(1L, "TDD with java", 1L);
        Session session = Session.valueOf(1L, "LMS", course.getId(), EnrollmentStatus.CLOSE
                , LocalDate.now(), LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());

        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session bySessionId = sessionRepository.findBySessionId(1L);
        assertThat(session.getTitle()).isEqualTo(bySessionId.getTitle());
        LOGGER.debug("image: {}", bySessionId);

    }
}
