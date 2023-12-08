package nextstep.courses.domain;

import nextstep.courses.domain.session.SessionDuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionDurationTest {

    @DisplayName("시작일과 종료일이 같을 때 예외를 던진다")
    @Test
    void durationSameException() {
        LocalDateTime startTime = LocalDateTime.of(2023, 12, 8, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 12, 8, 0, 0);
        assertThatThrownBy(() -> new SessionDuration(startTime, endTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작일은 종료일보다 이전이어야 합니다");
    }

    @DisplayName("종료일이 시작일보다 이를 때 예외를 던진다")
    @Test
    void durationException() {
        LocalDateTime startTime = LocalDateTime.of(2023, 12, 8, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 12, 7, 23, 59);
        assertThatThrownBy(() -> new SessionDuration(startTime, endTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작일은 종료일보다 이전이어야 합니다");
    }
}