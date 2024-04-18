package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

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

  @AfterEach
  void tearDown() {
    jdbcTemplate.update("ALTER TABLE session ALTER COLUMN id RESTART WITH 1");
    jdbcTemplate.update("ALTER TABLE session_image ALTER COLUMN id RESTART WITH 1");
  }

  @Test
  void crud() {
    SessionImage sessionImage = new SessionImage(300, 200, "jpg", 1024, "TEST", 1L);
    Session session = new ChargedSession(1L, LocalDate.now(), LocalDate.now().plusMonths(1L), List.of(sessionImage),
            OpenStatus.PREPARING, RecruitStatus.OPEN, 50, 800000L);
    int count = sessionRepository.save(session);
    assertThat(count).isEqualTo(1);

    Session savedSession = sessionRepository.findById(1L);
    assertThat(session.getCourseId()).isEqualTo(savedSession.getCourseId());

    LOGGER.debug("Session: {}", savedSession);
  }
}
