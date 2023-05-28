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
    Session session = new Session(SessionPayment.FREE, SessionProgressStatus.ACCEPTING, SessionRecruitmentStatus.RECRUITING, 1, currentTime, currentTime.plusDays(1), "https://oneny.com", currentTime, currentTime);

    Session savedSession = sessionRepository.save(session, 1L);
    Session findSession = sessionRepository.findById(savedSession.getId());

    assertThat(findSession).isEqualTo(savedSession);
  }

  @Test
  public void findByCourseId() {
    LocalDateTime currentTime = LocalDateTime.now();
    sessionRepository.save(new Session(SessionPayment.FREE, SessionProgressStatus.ACCEPTING, SessionRecruitmentStatus.RECRUITING, 1, currentTime, currentTime.plusDays(1), "https://oneny.com", currentTime, currentTime), 1L);
    sessionRepository.save(new Session(SessionPayment.PAID, SessionProgressStatus.PREPARING, SessionRecruitmentStatus.NOT_RECRUITING, 2, currentTime, currentTime.plusDays(1), "https://twony.com", currentTime, currentTime), 1L);

    List<Session> sessions = sessionRepository.findByCourseId(1L);
    assertThat(sessions).hasSize(2);
  }

  @Test
  public void saveSessionAndFindAllSessionUser() {
    LocalDateTime currentTime = LocalDateTime.now();
    Session savedSession = sessionRepository.save(new Session(SessionPayment.FREE, SessionProgressStatus.ACCEPTING, SessionRecruitmentStatus.RECRUITING, 1, currentTime, currentTime.plusDays(1), "https://oneny.com", currentTime, currentTime), 1L);

    savedSession.processEnrollment(NextStepUserTest.JAVAJIGI);
    sessionRepository.saveSessionUser(new SessionUser(savedSession, NextStepUserTest.JAVAJIGI, currentTime, currentTime));

    assertThat(savedSession.getSessionUsers().getSessionUsers()).hasSize(1);
  }

  @Test
  public void findSessionUser() {
    LocalDateTime currentTime = LocalDateTime.now();
    Session session = new Session(SessionPayment.FREE, SessionProgressStatus.ACCEPTING, SessionRecruitmentStatus.RECRUITING, 1, currentTime, currentTime.plusDays(1), "https://oneny.com", currentTime, currentTime);

    Session savedSession = sessionRepository.save(session, 1L);
    sessionRepository.saveSessionUser(new SessionUser(savedSession, NextStepUserTest.JAVAJIGI, currentTime, currentTime));

    SessionUser sessionUser = sessionRepository.findBySessionIdAndUserId(savedSession.getId(), NextStepUserTest.JAVAJIGI.getId());
    assertThat(sessionUser.getSessionUserStatus()).isEqualTo("신청");
  }
}
