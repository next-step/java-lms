package courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.infrastructure.JdbcSessionImageRepository;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private SessionRepository sessionRepository;

  @BeforeEach
  void setUp() {
    sessionRepository = new JdbcSessionRepository(jdbcTemplate, new JdbcSessionImageRepository(jdbcTemplate));
  }

  @Test
  void crud() {
    SessionImage sessionImage = new SessionImage(300, 200, "jpg", 1024, "TEST");
    Session session = new ChargedSession(1L, LocalDate.now(), LocalDate.now().plusMonths(1L), sessionImage, SessionStatus.OPEN, 50, 800000L);
    int count = sessionRepository.save(session);
    assertThat(count).isEqualTo(1);
    Session savedSession = sessionRepository.findById(1L);
    assertThat(session.getCourseId()).isEqualTo(savedSession.getCourseId());
    LOGGER.debug("Session: {}", savedSession);
  }
}
