package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SessionPeriodTest {

    public static SessionPeriod sessionPeriodMinMax = new SessionPeriod(LocalDateTime.MIN,
        LocalDateTime.MAX);

    @Test
    void 시작일은_종료일_보다_빨라야한다() {
        assertThatThrownBy(() -> new SessionPeriod(LocalDateTime.of(2023, 10, 19, 0, 0),
            LocalDateTime.of(2023, 10, 18, 0, 0)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("시작일은 종료일보다 빨라야합니다.");
    }

    @ParameterizedTest
    @CsvSource({
        "2023-01-01T12:00:00,2023-01-02T12:00:00,2023-01-01T13:00:00,false",
        "2023-01-01T12:00:00,2023-01-02T12:00:00,2023-01-01T11:00:00,true",
        "2023-01-01T12:00:00,2023-01-02T12:00:00,2023-01-02T13:00:00,true"
    })
    void isBetween(LocalDateTime startTime, LocalDateTime endTime, LocalDateTime time,
        boolean expected) {
        assertThat(new SessionPeriod(startTime, endTime).isNotBetween(time))
            .isEqualTo(expected);
    }

}
