package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NextStepUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private SessionRepository sessionRepository;

  @BeforeEach
  public void setUp() {
    sessionRepository = new JdbcSessionRepository(jdbcTemplate);
  }

  @Test
  public void saveAndFindById() {
    LocalDateTime currentTime = LocalDateTime.now();
    Session session = new Session(SessionPayment.FREE, SessionStatus.ACCEPTING, 1, currentTime, currentTime.plusDays(1), "https://oneny.com", currentTime, currentTime);

    Session savedSession = sessionRepository.save(session, 1L);
    Session findSession = sessionRepository.findById(savedSession.getId());

    assertThat(findSession).isEqualTo(savedSession);
  }

  @Test
  public void findByCourseId() {
    LocalDateTime currentTime = LocalDateTime.now();
    sessionRepository.save(new Session(SessionPayment.FREE, SessionStatus.ACCEPTING, 1, currentTime, currentTime.plusDays(1), "https://oneny.com", currentTime, currentTime), 1L);
    sessionRepository.save(new Session(SessionPayment.PAID, SessionStatus.PREPARING, 2, currentTime, currentTime.plusDays(1), "https://twony.com", currentTime, currentTime), 1L);

    List<Session> sessions = sessionRepository.findByCourseId(1L);
    assertThat(sessions).hasSize(2);
  }

  @Test
  public void saveAllAndFindAllSessionUser() {
    LocalDateTime currentTime = LocalDateTime.now();
    Session savedSession = sessionRepository.save(new Session(SessionPayment.FREE, SessionStatus.ACCEPTING, 1, currentTime, currentTime.plusDays(1), "https://oneny.com", currentTime, currentTime), 1L);
    SessionUsers sessionUsers = new SessionUsers(3);
    sessionUsers.enroll(new SessionUser(savedSession, NextStepUserTest.JAVAJIGI, currentTime, currentTime));

    sessionRepository.saveAllSessionUser(sessionUsers);

    List<SessionUser> findAllSessionUser = sessionRepository.findAllSessionUserBySessionId(savedSession.getId());
    assertThat(findAllSessionUser).hasSize(1);
  }
}
