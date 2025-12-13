package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class SessionInfoTest {

    @Test
    void imageNull_throwsExcepiton() {
        assertThatThrownBy(() -> new SessionInfo(PeriodTest.P1, SessionPricingTest.FREE_SP, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("강의 커버 이미지는 필수입니다");
    }
}