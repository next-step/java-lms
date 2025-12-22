package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.sessions.domain.image.SessionImageTest;
import org.junit.jupiter.api.Test;

class SessionInfoTest {

    public static final SessionInfo INFO = new SessionInfo(PeriodTest.P1, SessionImageTest.IMAGE);

    @Test
    void imageNull_throwsExcepiton() {
        assertThatThrownBy(() -> new SessionInfo(PeriodTest.P1, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("강의 커버 이미지는 필수입니다");
    }
}