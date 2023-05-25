package nextstep.courses;

import nextstep.courses.domain.SessionPeriod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionPeriodTest {

    @DisplayName("period 생성")
    @Test
    void create() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(4);

        SessionPeriod sessionPeriod = new SessionPeriod(startDate, endDate);

        assertThat(sessionPeriod).isEqualTo(new SessionPeriod(startDate, endDate));
    }

    @DisplayName("시작일이 종료일보다 늦는 경우 에러 발생")
    @Test
    void ExceptionTest() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.minusDays(4);

        assertThatThrownBy(() -> new SessionPeriod(startDate, endDate)).isInstanceOf(IllegalArgumentException.class);
    }

}
