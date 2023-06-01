package nextstep.courses.domain;

import nextstep.courses.domain.session.SessionPeriod;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@DisplayName("강의 시간 테스트")
class SessionPeriodTest {

    @DisplayName("강의 시간 종료일은 시작일보다 나중이여야 한다")
    @Test
    void lecturePeriod() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() ->
                        new SessionPeriod(
                                LocalDate.now().minusDays(1),
                                LocalDate.now().minusDays(2))
                )
                .withMessage("Start date must be before end date");
    }

    @DisplayName("강의 기간을 구할 수 있다")
    @Test
    void lectureDuration() {
        SessionPeriod sessionPeriod = new SessionPeriod(
                LocalDate.now(),
                LocalDate.now().plusDays(20)
        );

        int lectureDuration = sessionPeriod.sessionDuration();

        Assertions.assertThat(lectureDuration).isEqualTo(20);
    }
}
