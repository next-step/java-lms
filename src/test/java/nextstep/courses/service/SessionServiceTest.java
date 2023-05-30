package nextstep.courses.service;

import nextstep.courses.domain.*;
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
    session = sessionService.save(new Session(SessionPayment.FREE, SessionProgressStatus.ACCEPTING, SessionRecruitmentStatus.RECRUITING, 1, currentTime, currentTime.plusDays(1), "https://oneny.com", currentTime, currentTime), 1L);
    sessionService.enrollUser(session.getId(), NextStepUserTest.JAVAJIGI.getUserId());
  }

  @Test
  public void session_enrollment_count() {
    Session findSession = sessionService.findById(session.getId());
    assertThat(findSession.getSessionUsers().getSessionUsers()).hasSize(1);
  }

  @Test
  public void session_만석() {
    assertThatThrownBy(() -> sessionService.enrollUser(session.getId(), NextStepUserTest.SANJIGI.getUserId()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 세션의 수강 인원이 만석되었습니다.");
  }

  @Test
  public void session_비모집_진행중() {
    LocalDateTime currentTime = LocalDateTime.now();
    Session endingSession = sessionService.save(new Session(SessionPayment.FREE, SessionProgressStatus.IN_PROGRESSING, SessionRecruitmentStatus.NOT_RECRUITING, 1, currentTime, currentTime.plusDays(1), "https://oneny.com", currentTime, currentTime), 1L);

    assertThatThrownBy(() -> sessionService.enrollUser(endingSession.getId(), NextStepUserTest.SANJIGI.getUserId()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("수강신청은 강의 상태가 모집중일 때만 가능합니다. 현재 수강 상태 : 진행중");
  }

  @Test
  public void session_신청_후_상태_확인() {
    SessionUser sessionUser = sessionService.findBySessionIdAndUserId(session.getId(), NextStepUserTest.JAVAJIGI.getId());
    assertThat(sessionUser.getSessionUserStatus()).isEqualTo("신청");
  }

  @Test
  public void session_승인_후_상태_확인() {
    sessionService.approveEnrollment(session.getId(), NextStepUserTest.JAVAJIGI.getId());

    SessionUser sessionUser = sessionService.findBySessionIdAndUserId(session.getId(), NextStepUserTest.JAVAJIGI.getId());
    assertThat(sessionUser.getSessionUserStatus()).isEqualTo("승인");
  }

  @Test
  public void session_거절_후_상태_확인() {
    sessionService.rejectEnrollment(session.getId(), NextStepUserTest.JAVAJIGI.getId());

    SessionUser sessionUser = sessionService.findBySessionIdAndUserId(session.getId(), NextStepUserTest.JAVAJIGI.getId());
    assertThat(sessionUser.getSessionUserStatus()).isEqualTo("거절");
  }
}
