package nextstep.courses.domain;

import nextstep.sessions.domain.SessionPeriod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SessionPeriodTest {
    
    @Test
    @DisplayName("강의 기간 중 강의 시작일이 8자리 형식이 아닐 때 오류를 발생시킨다.")
    void sessionStartDateValidateTest() {
        assertThatThrownBy(
                () -> new SessionPeriod("2011111", "20111111")
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("날짜가 형식에 맞지 않습니다.");
    }

    @Test
    @DisplayName("강의 기간 중 강의 종료일이 8자리 형식이 아닐 때 오류를 발생시킨다.")
    void sessionEndDateValidateTest() {
        assertThatThrownBy(
                () -> new SessionPeriod("20111111", "2011111")
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("날짜가 형식에 맞지 않습니다.");
    }

    @Test
    @DisplayName("강의 기간 중 시작일이 숫자가 아닌 다른 타입이 들어오면 오류를 발생시킨다.")
    void sessionStartDateTypeValidateTest() {
        assertThatThrownBy(
                () -> new SessionPeriod("20a11111", "20111111")
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("강의 시작 및 종료일은 숫자형식이어야 합니다.");
    }
    
    @Test
    @DisplayName("강의 기간 중 종료일이 숫자가 아닌 다른 타입이 들어오면 오류를 발생시킨다.")
    void sessionEndDateTypeValidateTest() {
        assertThatThrownBy(
                () -> new SessionPeriod("20111111", "20ㅁ11111")
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("강의 시작 및 종료일은 숫자형식이어야 합니다.");
    }
}
