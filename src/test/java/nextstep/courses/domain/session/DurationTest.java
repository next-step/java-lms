package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDateTime;
import nextstep.courses.InvalidTimeRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DurationTest {

    @Test
    @DisplayName("시작시한이 종료시한보다 늦을 수 없다.")
    void 강의기간_시작시한_종료시한이후() {
        LocalDateTime startDate = LocalDateTime.parse("2023-05-02T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2023-05-01T00:00:00");
        assertThatExceptionOfType(InvalidTimeRangeException.class)
            .isThrownBy(() -> Duration.of(startDate, endDate))
            .withMessageMatching("시작기한은 종료기한 이후일 수 없습니다.");
    }

    @Test
    @DisplayName("같은 상태를 갖는 두 강의기간은 동일한 강의기간이다.")
    void 강의기간_생성() throws InvalidTimeRangeException {
        LocalDateTime startDate = LocalDateTime.parse("2023-05-01T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2023-05-31T23:59:59");
        assertThat(Duration.of(startDate, endDate)).isEqualTo(Duration.of(startDate, endDate));
    }

}