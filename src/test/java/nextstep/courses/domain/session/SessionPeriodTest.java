package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.courses.domain.session.SessionPeriod.INVALID_SESSION_PERIOD;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SessionPeriodTest {

    @Test
    @DisplayName("강의 시간 객체 생성")
    void testSessionPeriod() {
        // given
        LocalDateTime startDate = LocalDateTime.of(2024,1,1,0,0,0);
        LocalDateTime endDate = LocalDateTime.of(2024,5,1,0,0,0);

        // when
        SessionPeriod sessionPeriod = SessionPeriod.of(startDate, endDate);

        // then
        assertAll(
                "SessionPeriod is correctly set",
                () -> assertEquals(sessionPeriod.getStartDate(), startDate),
                () -> assertEquals(sessionPeriod.getEndDate(), endDate)
        );
    }

    @Test
    @DisplayName("강의 시간 객체 생성 실패 - 강의 시작 시간이 종료 시간보다 늦음")
    void testSessionPeriod_startDateIsLaterThanEndDate_ShouldThrowException() {
        // given
        LocalDateTime startDate = LocalDateTime.of(2024,5,1,0,0,0);
        LocalDateTime endDate = LocalDateTime.of(2024,1,1,0,0,0);

        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            SessionPeriod.of(startDate, endDate);
        }).withMessageContaining(INVALID_SESSION_PERIOD);
    }

}
