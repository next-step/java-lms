package nextstep.courses.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class SessionTimeTest {
    @Test
    @DisplayName("강의 날짜의 유효성 확인.")
    void check_session_status_recruiting() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SessionTime(LocalDateTime.of(2024, 4, 16, 12, 5), LocalDateTime.of(2024, 4, 15, 2, 5));
        });
    }
}
