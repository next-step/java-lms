package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SessionPeriodTest {
    @Test
    @DisplayName("강의는 시작일과 종료일을 가진다")
    void period() {
        LocalDateTime startedAt = LocalDateTime.now();
        LocalDateTime endAt = startedAt.plusMonths(1);
        SessionPeriod sessionPeriod = new SessionPeriod(startedAt, endAt);
        assertThat(sessionPeriod).isEqualTo(new SessionPeriod(startedAt, endAt));
    }

    @Test
    @DisplayName("강의 시작일은 종료일 이전이어야 한다")
    void validatePeriod() {
        LocalDateTime startedAt = LocalDateTime.now();
        LocalDateTime endAt = startedAt.minusMonths(1);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new SessionPeriod(startedAt, endAt))
                .withMessageMatching("강의 시작일은 종료일 이전이어야 합니다.");
    }
}
