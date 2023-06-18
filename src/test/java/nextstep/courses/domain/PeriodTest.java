package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PeriodTest {

    @Test
    void create_시작시간이끝시간보다전_실패() {
        assertThatThrownBy(() -> new Period(LocalDateTime.now().minusSeconds(1)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}