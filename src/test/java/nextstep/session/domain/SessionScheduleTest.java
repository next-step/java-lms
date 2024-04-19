package nextstep.session.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SessionScheduleTest {

    private static SessionSchedule sessionSchedule;

    @BeforeAll
    public static void initializeSchedule() {
        LocalDate startDate = LocalDate.of(2024, 4, 10);
        LocalDate endDate = LocalDate.of(2024, 6, 30);
        sessionSchedule = new SessionSchedule(startDate, endDate);
    }

    @Test
    public void 종료_날짜가_시작_날짜보다_빠를_경우_생성_불가능() {
        assertThatThrownBy(() -> {
            new SessionSchedule(LocalDate.now(), LocalDate.now().minusDays(1));
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("종료 날짜는 시작 날짜보다 빠를 수 없습니다.");
    }

    @Test
    public void 강의_시작일_종료일_사이에_강의_신청_가능() {
        assertThat(sessionSchedule.isAbleToRegister(LocalDate.of(2024, 5, 1))).isTrue();
    }

    @Test
    public void 강의_시작일_전에는_강의_신청_불가능() {
        assertThat(sessionSchedule.isAbleToRegister(LocalDate.of(2024, 4, 9))).isFalse();
    }

    @Test
    public void 강의_종료일_이후에는_강의_신청_불가능() {
        assertThat(sessionSchedule.isAbleToRegister(LocalDate.of(2024, 6, 30))).isFalse();
    }
}
