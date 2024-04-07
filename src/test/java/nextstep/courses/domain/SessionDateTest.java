package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class SessionDateTest {

    private static final String START_DATE_CANT_LATE_THEN_END_DATE = "강의 시작일은 종료일 이전이어야 합니다.";

    @Test
    @DisplayName("startDate가 endDate보다 크면 예외 발생")
    void create_exception() {
        Assertions.assertThatThrownBy(() -> SessionDate.of(LocalDateTime.MAX, LocalDateTime.MIN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(START_DATE_CANT_LATE_THEN_END_DATE);
    }
}
