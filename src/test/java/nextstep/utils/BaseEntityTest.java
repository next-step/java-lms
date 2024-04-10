package nextstep.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class BaseEntityTest {

    @DisplayName("시작일이 종료일보다 앞설 경우 예외를 반환한다")
    @Test
    void name() {
        LocalDateTime start = LocalDateTime.of(2024, 4, 12, 17, 30);
        LocalDateTime end = LocalDateTime.of(2024, 4, 10, 17, 30);

        Assertions.assertThatThrownBy(() -> new BaseEntity(1L, start, end))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작일은 종료일보다 앞서야 됩니다.");
    }

}
