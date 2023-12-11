package nextstep.courses.domain.session;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class PeriodTest {

    @DisplayName("코스의 생성일보다 세션 시작날짜가 이전이면 false를 반환합니다.")
    @Test
    void preSession() {
        // given
        Period period = createPeriod(LocalDateTime.of(2023, 11, 1, 14, 0), LocalDateTime.of(2023, 11, 20, 0, 0));
        // when
        boolean result = period.isAfter(LocalDateTime.of(2023, 12, 1, 0, 0));
        // then
        Assertions.assertThat(result).isFalse();
    }

    @DisplayName("코스의 생성일보다 세션 시작날짜가 이후이면 true를 반환합니다.")
    @Test
    void afterSession() {
        // given
        Period period = createPeriod(LocalDateTime.of(2023, 11, 1, 14, 0),
                LocalDateTime.of(2023, 11, 20, 0, 0));
        // when
        boolean result = period.isAfter(LocalDateTime.of(2023, 10, 1, 0, 0));
        // then
        Assertions.assertThat(result).isTrue();
    }

    @DisplayName("입력받은 세션의 시작날짜가 종료날짜보다 이후면 false를 반환합니다.")
    @Test
    void afterEnd() {
        Assertions.assertThatThrownBy(() -> createPeriod(LocalDateTime.of(2023, 11, 1, 14, 0),
                        LocalDateTime.of(2023, 10, 20, 0, 0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작 날짜가 종료 날짜 보다 이후일 수 없습니다.");
    }

    private Period createPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return new Period(startDateTime, endDateTime);
    }
}