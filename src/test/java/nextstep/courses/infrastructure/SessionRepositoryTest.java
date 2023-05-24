package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionPayment;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

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
    System.out.println("savedSession = " + savedSession);

    Session findSession = sessionRepository.findById(savedSession.getId());
    System.out.println("findSession = " + findSession);
    Assertions.assertThat(findSession).isEqualTo(savedSession);
  }

}
