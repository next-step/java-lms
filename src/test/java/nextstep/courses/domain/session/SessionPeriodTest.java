package nextstep.courses.domain.session;

import nextstep.courses.domain.session.info.detail.SessionPeriod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionPeriodTest {
    private static final LocalDateTime START_DATE = LocalDateTime.now();
    private static final LocalDateTime END_DATE = START_DATE.plusDays(30);

    @Test
    @DisplayName("시작일이 종료일보다 늦으면 예외가 발생한다")
    void validateDates() {
        // given
        LocalDateTime invalidStartDate = END_DATE.plusDays(1);

        // when & then
        assertThatThrownBy(() -> new SessionPeriod(invalidStartDate, END_DATE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작일은 종료일보다 이전이어야 합니다.");
    }

    @Test
    @DisplayName("시작일과 종료일을 정상적으로 설정한다")
    void createSessionPeriod() {
        // given
        SessionPeriod period = new SessionPeriod(START_DATE, END_DATE);

        // when & then
        assertThat(period.getStartDate()).isEqualTo(START_DATE);
        assertThat(period.getEndDate()).isEqualTo(END_DATE);
    }
} 