package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionCoverUrlTest {

  private SessionCoverUrl sessionCoverUrl;

  @BeforeEach
  public void setUp() {
    sessionCoverUrl = new SessionCoverUrl("https://www.oneny.com");
  }

  @Test
  @DisplayName("URL 변경 테스트")
  public void change_CoverImg() {
    sessionCoverUrl = sessionCoverUrl.changeCoverImg("https://www.twony.com");

    assertThat(sessionCoverUrl).isEqualTo(new SessionCoverUrl("https://www.twony.com"));
  }

  @Test
  @DisplayName("url 형식이 아닌 경우 IllegalArgumentException throw")
  public void 잘못된_URL_형식() {
    assertThatThrownBy(() -> new SessionCoverUrl("oneny.com"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("URL 형식이어야 합니다. 현재 URL : oneny.com");
  }
}
