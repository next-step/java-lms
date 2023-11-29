package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


class DurationTest {

    @Test
    @DisplayName("종료일은 시작일보다 빠를 수 없다.")
    void endDateShouldBeAfterStartDate() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> {
            new Duration(LocalDateTime.parse("2020-01-01T00:00:00"), LocalDateTime.parse("2019-01-01T00:00:00"));
        });
    }

}
