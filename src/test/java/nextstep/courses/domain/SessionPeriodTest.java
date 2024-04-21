package nextstep.courses.domain;

import nextstep.sessions.domain.SessionPeriod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SessionPeriodTest {
    
    @Test
    @DisplayName("강의 기간 중 강의 시작일이 8자리 형식이 아닐 때 오류를 발생시킨다.")
    void sessionStartDateValidateTest() {
        assertThatThrownBy(
                () -> new SessionPeriod(LocalDate.of(2024, 04, 8), LocalDate.of(2024, 04, 7))
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("종료 날짜는 시작 날짜보다 빠를 수 없습니다.");
    }
}
