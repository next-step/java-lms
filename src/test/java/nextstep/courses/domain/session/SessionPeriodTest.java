package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionPeriodTest {


    @DisplayName("유효한 시작일과 종료일로 SessionPeriod 생성한다.")
    @Test
    void createSessionPeriod() {
        LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 1, 10, 18, 0);

        SessionPeriod period = SessionPeriod.of(startDate, endDate);

        assertThat(period.getStartDate()).isEqualTo(startDate);
        assertThat(period.getEndDate()).isEqualTo(endDate);
    }

    @DisplayName("시작일이 종료일보다 이후일 경우 예외가 발생한다.")
    @Test
    void throwExceptionWhenInvalidDate() {
        LocalDateTime startDate = LocalDateTime.of(2024, 1, 10, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 1, 1, 18, 0);

        assertThatThrownBy(() -> SessionPeriod.of(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작 날짜는 종료 날짜보다 이후일 수 없습니다.");
    }

}
