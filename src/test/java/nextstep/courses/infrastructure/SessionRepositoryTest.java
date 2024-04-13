package nextstep.courses.infrastructure;

import nextstep.config.BeanConfig;
import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(BeanConfig.class)
public class SessionRepositoryTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private SimpleJdbcInsert simpleJdbcInsert;

  private SessionRepository sessionRepository;

  @BeforeEach
  void setUp() {
    sessionRepository = new JdbcSessionRepository(jdbcTemplate, new JdbcSessionImageRepository(jdbcTemplate, simpleJdbcInsert));
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
