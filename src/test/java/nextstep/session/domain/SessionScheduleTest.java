package nextstep.session.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class SessionScheduleTest {

    @Test
    public void 종료_날짜가_시작_날짜보다_빠를_경우_생성_불가능() {
        assertThatThrownBy(() -> {
            new SessionSchedule(LocalDate.now(), LocalDate.now().minusDays(1));
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("종료 날짜는 시작 날짜보다 빠를 수 없습니다.");
    }

    @Test
    public void 강의_스케줄_생성() {
        LocalDate startDate = LocalDate.of(2024, 4, 10);
        LocalDate endDate = LocalDate.of(2024, 6, 30);
        SessionSchedule sessionSchedule = new SessionSchedule(startDate, endDate);
        assertThat(sessionSchedule.isAbleToRegister(LocalDate.of(2024, 4, 9))).isFalse();
        assertThat(sessionSchedule.isAbleToRegister(LocalDate.of(2024, 6, 30))).isFalse();
        assertThat(sessionSchedule.isAbleToRegister(LocalDate.of(2024, 5, 1))).isTrue();
    }
}
