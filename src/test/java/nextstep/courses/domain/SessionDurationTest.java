package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionDurationTest {

    @Test
    @DisplayName("시작일과 종료일이 같으면 exception")
    void start_equals_end_exception() {
        LocalDateTime start = LocalDateTime.of(2025, 12, 15, 00, 00);
        LocalDateTime end = LocalDateTime.of(2025, 12, 15, 00, 00);

        assertThatThrownBy(() -> new SessionDuration(start, end))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("시작일이 종료일보다 크면 exception")
    void start_after_end_exception() {
        LocalDateTime start = LocalDateTime.of(2025, 12, 15, 14, 00);
        LocalDateTime end = LocalDateTime.of(2025, 12, 15, 13, 00);

        assertThatThrownBy(() -> new SessionDuration(start, end))
                .isInstanceOf(IllegalArgumentException.class);
    }
}