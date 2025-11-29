package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class SessionRangeTest {

    @Test
    void 강의일정_정상_생성() {
        LocalDateTime startDate = LocalDateTime.of(2025, 11, 1, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2025, 11, 30, 1, 59, 59);
        SessionRange range = new SessionRange(startDate, endDate);

        assertThat(range.getStartDate()).isEqualTo(startDate);
        assertThat(range.getEndDate()).isEqualTo(endDate);
    }

    @Test
    void 강의_종료일이_시작일보다_빠른경우_에러발생() {
        LocalDateTime startDate = LocalDateTime.of(2025, 11, 30, 1, 59, 59);
        LocalDateTime endDate = LocalDateTime.of(2025, 11, 1, 0, 0, 0);

        assertThatThrownBy(() -> new SessionRange(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
