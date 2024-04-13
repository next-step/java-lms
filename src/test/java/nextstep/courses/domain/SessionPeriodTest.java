package nextstep.courses.domain;

import nextstep.courses.exception.SessionPeriodRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.fixture.SessionPeriodFixture.*;
import static nextstep.courses.exception.CourseExceptionMessage.INVALID_SESSION_DURATION;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class SessionPeriodTest {

    @Test
    @DisplayName("[성공] 강의 시작일시와 종료일시를 생성한다.")
    void 강의_시작종료일시() {
        assertThatNoException()
                .isThrownBy(() -> sessionDuration(SESSION_START_AT, SESSION_END_AT));
    }

    @Test
    @DisplayName("[실패] 강의의 시작일시가 종료일시보다 늦은 경우 InvalidSessionDurationException 이 발생한다.")
    void 유효하지_않은_강의_시작종료일시() {
        assertThatExceptionOfType(SessionPeriodRangeException.class)
                .isThrownBy(() -> sessionDuration(SESSION_END_AT, SESSION_START_AT))
                .withMessageContaining(INVALID_SESSION_DURATION.getMessage());
    }
}
