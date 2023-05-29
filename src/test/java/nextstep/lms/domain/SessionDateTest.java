package nextstep.lms.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionDateTest {

    @Test
    @DisplayName("수강 날짜 생성 테스트")
    void createSessionDateTest() {
        LocalDate startDate = LocalDate.of(2023, 5, 28);
        LocalDate endDate = LocalDate.of(2023, 5, 30);

        SessionDate sessionDate = new SessionDate(startDate, endDate);

        assertThat(sessionDate.getStartDate())
                .isEqualTo(startDate);

        assertThat(sessionDate.getEndDate())
                .isEqualTo(endDate);
    }

    @Test
    @DisplayName("수강 종료 날짜가 수강 시작 날짜보다 앞일 경우 에러 테스트")
    void validateDateTest() {
        LocalDate startDate = LocalDate.of(2023, 5, 28);
        LocalDate endDate = LocalDate.of(2023, 5, 27);

        assertThatThrownBy(() -> new SessionDate(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("종료 날짜가 특정 날짜를 지났을 경우 테스트")
    void isAfterDateTest() {
        LocalDate startDate = LocalDate.of(2023, 5, 27);
        LocalDate endDate = LocalDate.of(2023, 5, 27);
        SessionDate sessionDate = new SessionDate(startDate, endDate);

        LocalDate now = LocalDate.of(2023, 5, 28);

        assertThat(sessionDate.isAfterEndDate(now))
                .isTrue();

    }

}
