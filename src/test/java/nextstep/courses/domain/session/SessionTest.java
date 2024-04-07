package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.courses.domain.session.CoverImageDetailInfo.*;
import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {

  @Test
  @DisplayName("정상 강의 기간, 강의 정보, 커버 이미지 정보를 입력하는 경우" +
      "Session 생성 테스트")
  void sessionTest() {
    SessionPeriod sessionPeriod = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(10));
    SessionInfo sessionInfo = new SessionInfo("강의명", SessionStatus.READY, true, 0L,null);
    CoverImage coverImage = new CoverImage("이미지명", ImageType.PNG, new CoverImageDetailInfo(ONE_MB, MIN_COVER_IMAGE_WIDTH, MIN_COVER_IMAGE_HEIGHT));

    Session session = new Session(1L, sessionPeriod, sessionInfo, coverImage);

    assertThat(session.getId()).isEqualTo(1L);
    assertThat(session.getSessionPeriod().isCorrectPeriod()).isTrue();
  }
}
