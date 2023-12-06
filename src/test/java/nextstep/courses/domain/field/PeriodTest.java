package nextstep.courses.domain.field;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PeriodTest {
    private Period period = new Period();

    @Test
    @DisplayName("이미 시작되었는지 검증")
    public void assertIsStarted() {
        period.start();

        assertThat(period.isStarted()).isTrue();
    }

    @Test
    @DisplayName("이미 끝났는지 검증")
    public void assertIsEnded() {
        period.end();

        assertThat(period.isEnded()).isTrue();
    }
}