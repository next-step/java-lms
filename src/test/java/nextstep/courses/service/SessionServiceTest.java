package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.users.domain.NextStepUser;
import nextstep.users.domain.NextStepUserTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
public class SessionServiceTest {

  @Autowired
  private SessionService sessionService;

  @Autowired
  private SessionRepository sessionRepository;

  @Test
  public void session_nextstep_user_enroll() {
    LocalDateTime currentTime = LocalDateTime.now();
    List<NextStepUser> nextStepUsers = new ArrayList<>(List.of(NextStepUserTest.JAVAJIGI, NextStepUserTest.SANJIGI));
    Session session = sessionRepository.save(new Session(1L, SessionPayment.FREE, SessionStatus.ACCEPTING, 1, currentTime, currentTime.plusDays(1), "https://oneny.com", currentTime, currentTime), 1L);

    assertAll(
            () -> assertThatThrownBy(() -> sessionService.enrollUsers(session.getId(), nextStepUsers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 세션의 수강 인원이 만석되었습니다."),
            () -> {
              List<SessionUser> sessionUsers = sessionRepository.findAllSessionUserBySessionId(session.getId());
              assertThat(sessionUsers).hasSize(1);
            }
    );
  }
}
