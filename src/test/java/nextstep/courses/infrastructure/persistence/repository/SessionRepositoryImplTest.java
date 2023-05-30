package nextstep.courses.infrastructure.persistence.repository;

import static nextstep.courses.domain.SessionStatus.IN_PROGRESS;
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
  void 임시_데이터에서_수강생_2명_있는_Session_100L_조회_및_값_검증() {
    Session session = sessionRepository.findById(100L);
    assertThat(session).isNotNull();

    assertThat(session.getId()).isEqualTo(100L);
    assertThat(session.getSessionInfo()).isEqualTo(
        new SessionInfo("Session 1 Belong To Course 1", "Session 1 Description"));
    assertThat(session.getSessionType()).isEqualTo(FREE);
    assertThat(session.getSessionStatus()).isEqualTo(IN_PROGRESS);
    assertThat(session.getStudents().size()).isEqualTo(2);
  }

  @Test
  void 임시_데이터에_존재하지_않는_경우_101L_Session_조회_실패() {
    assertThatThrownBy(() -> sessionRepository.findById(101L))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("존재하지 않는 세션입니다.");
  }
}