package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class PeriodTest {

    @Test
    void 강의_시작일이_오늘_이후가_아닐_경우_에러() {
        LocalDate startDate = LocalDate.of(2023, 12, 9);
        LocalDate endDate = LocalDate.of(2024, 1, 31);
        assertThatThrownBy(() -> new Period(startDate, endDate)).isInstanceOf(
            IllegalArgumentException.class).hasMessage("강의 시작일은 오늘 날짜 이후로 설정할 수 있습니다.");
    }

    @Test
    void 강의_종료일이_강의_시작일_이후가_아닐_경우_에러() {
        LocalDate startDate = LocalDate.of(2024, 1, 10);
        LocalDate endDate = LocalDate.of(2024, 1, 9);
        assertThatThrownBy(() -> new Period(startDate, endDate)).isInstanceOf(
            IllegalArgumentException.class).hasMessage("강의 종료일은 강의 시작일 이후로 설정할 수 있습니다.");
    }
}
