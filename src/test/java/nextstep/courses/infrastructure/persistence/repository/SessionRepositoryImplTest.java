package nextstep.courses.infrastructure.persistence.repository;

import static nextstep.courses.domain.SessionStatus.RECRUITING;
import static nextstep.courses.domain.SessionType.FREE;
import static org.assertj.core.api.Assertions.*;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionInfo;
import nextstep.courses.domain.SessionRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SessionRepositoryImplTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryImplTest.class);

  @Autowired
  private SessionRepository sessionRepository;

  @Test
  void findById() {
    Session session = sessionRepository.findById(100L);
    assertThat(session).isNotNull();

    assertThat(session.getId()).isEqualTo(100L);
    assertThat(session.getSessionInfo()).isEqualTo(
        new SessionInfo("Session 1 Belong To Course 1", "Session 1 Description"));
    assertThat(session.getSessionType()).isEqualTo(FREE);
    assertThat(session.getSessionStatus()).isEqualTo(RECRUITING);
    assertThat(session.getStudents().size()).isEqualTo(2);
  }
}