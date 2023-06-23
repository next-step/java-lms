package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class SessionPeriodTest {

    @Test
    @DisplayName("강의 시작일이 강의 종료일보다 빠르면 예외를 던지지 않는다.")
    void create_CreateAtBeforeThanClosedAt_NoException() {
        LocalDateTime createAt = LocalDateTime.now();
        LocalDateTime closeAt = createAt.plusDays(5);
    }

    @Test
    @DisplayName("강의 시작일이 강의 종료일보다 느리면 예외를 던진다.")
    void create_CreateAtAfterThanClosedAt_NoException() {
        LocalDateTime createAt = LocalDateTime.now();
        LocalDateTime closeAt = createAt.minusDays(5);

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new SessionPeriod(createAt, closeAt));
    }

}
