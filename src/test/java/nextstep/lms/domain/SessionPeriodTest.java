package nextstep.lms.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class SessionPeriodTest {
    @DisplayName("강의 시작 후에는 수강신청 불가능")
    @Test
    void 강의_신청_불가() {
        SessionPeriod sessionPeriod = new SessionPeriod(LocalDateTime.of(2023, 12, 5, 0, 0, 0),
                LocalDateTime.of(2023, 12, 31, 23, 59, 59));

        assertThatThrownBy(() -> sessionPeriod.canEnrollCheck(LocalDateTime.now()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의 시작 후에는 수강신청할 수 없습니다.");
    }
}