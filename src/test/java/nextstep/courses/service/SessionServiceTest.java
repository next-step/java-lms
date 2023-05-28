package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionPayment;
import nextstep.courses.domain.SessionProgressStatus;
import nextstep.courses.domain.SessionUser;
import nextstep.users.domain.NextStepUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class SessionServiceTest {

  @Autowired
  private SessionService sessionService;
  private Session session;

  @BeforeEach
  public void setUp() {
    LocalDateTime currentTime = LocalDateTime.now();
    session = sessionService.save(new Session(SessionPayment.FREE, SessionProgressStatus.ACCEPTING, 1, currentTime, currentTime.plusDays(1), "https://oneny.com", currentTime, currentTime), 1L);
    sessionService.enrollUsers(session.getId(), NextStepUserTest.JAVAJIGI.getUserId());
  }

  @Test
  public void session_enrollment_count() {
    List<SessionUser> sessionUsers = sessionService.findSessionUsersBySessionId(session.getId());
    assertThat(sessionUsers).hasSize(1);
  }

  @Test
  public void session_만석() {
    assertThatThrownBy(() -> sessionService.enrollUsers(session.getId(), NextStepUserTest.SANJIGI.getUserId()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 세션의 수강 인원이 만석되었습니다.");
  }
}
