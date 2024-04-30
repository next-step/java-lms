package nextstep.sessions.domain;

import nextstep.sessions.domain.SessionPeriod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SessionPeriodTest {
    
    @Test
    @DisplayName("강의 종료 날짜가 강의 시작 날짜보다 빠를 시 오류를 발생시킨다.")
    void sessionStartDateValidateTest() {
        assertThatIllegalArgumentException().isThrownBy(
                        () -> new SessionPeriod(LocalDate.of(2024, 04, 8), LocalDate.of(2024, 04, 7))
                ).withMessageContaining("종료 날짜는 시작 날짜보다 빠를 수 없습니다.");
    }
}
