package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class SessionStateTest {

    @Test
    @DisplayName("강의의 상태가 준비 중일 때, 오늘 날짜가 강의 시작일 이전인지 확인")
    void 강의_상태_확인_준비중 () {
        LocalDate startDate = LocalDate.of(2023,12,20);
        LocalDate currentDate = LocalDate.of(2023,12,15);

        assertThat(SessionState.PREPARING.checkStatus(startDate, currentDate)).isTrue();
    }

    @Test
    @DisplayName("강의의 상태가 모집 중일 때, 오늘 날짜가 강의 시작일 이전인지 확인")
    void 강의_상태_확인_모집중 () {
        LocalDate startDate = LocalDate.of(2023,12,20);
        LocalDate currentDate = LocalDate.of(2023,12,15);

        assertThat(SessionState.RECRUITING.checkStatus(startDate, currentDate)).isTrue();
    }

    @Test
    @DisplayName("강의의 상태가 종료일 때, 오늘 날짜가 강의 종료일 이후인지 확인")
    void 강의_상태_확인_종료 () {
        LocalDate endDate = LocalDate.of(2023,12,20);
        LocalDate currentDate = LocalDate.of(2023,12,25);

        assertThat(SessionState.END.checkStatus(endDate, currentDate)).isTrue();
    }

}