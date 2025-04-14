package nextstep.sessions.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import nextstep.image.domain.Dimension;
import nextstep.sessions.domain.EnrollmentManager;
import nextstep.image.domain.Image;
import nextstep.image.domain.ImageMeta;
import nextstep.image.domain.ImageType;
import nextstep.payments.PaidPolicy;
import nextstep.sessions.domain.Period;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionInformation;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.SessionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class SessionRepositoryTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

  @Autowired
  JdbcTemplate jdbcTemplate;

  SessionRepository sessionRepository;

  @BeforeEach
  void setUp() {
    sessionRepository = new JdbcSessionRepository(jdbcTemplate);
  }

  private Session createSampleSession() {
    SessionInformation info = new SessionInformation(
        "TDD",
        new Period(LocalDate.now(), LocalDate.now().plusDays(10)),
        new Image(
            new ImageMeta("cover.png", ImageType.PNG, 600_000),
            new Dimension(300, 200)
        )
    );
    EnrollmentManager manager = new EnrollmentManager(new PaidPolicy(20, 20000L));
    manager.updateStatus(SessionStatus.OPEN);
    return new Session(info, manager);
  }

  @Test
  @DisplayName("세션 저장 및 조회")
  void save_and_find() {
    Long courseId = 3L;
    Session session = createSampleSession();
    sessionRepository.save(session, courseId);

    List<Session> found = sessionRepository.findAllByCourseId(courseId);

    assertThat(found).hasSize(1);
    assertThat(found.get(0).info().title()).isEqualTo("TDD");
  }
}
