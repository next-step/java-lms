package nextstep.courses.domian;

import nextstep.courses.InvalidValueException;
import nextstep.courses.domain.SessionDuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionDurationTest {

    @Test
    @DisplayName("정상적인 시작일과 종료일을 입력할 경우 강의기간은 정상 생성된다.")
    void createSessionDuration() {
        LocalDateTime startDate = LocalDateTime.now();
        SessionDuration sessionDuration = new SessionDuration(startDate.plusDays(1), startDate.plusDays(2));

        assertThat(sessionDuration).isEqualTo(new SessionDuration(startDate.plusDays(1), startDate.plusDays(2)));
    }

    @Test
    @DisplayName("시작일 또는 종료일이 null일 경우 오류가 발생한다.")
    void createSessionDuration_null() {
        assertThatThrownBy(() -> new SessionDuration(null, null))
                .isInstanceOf(InvalidValueException.class)
                .hasMessage("강의 시작 혹은 종료 날짜는 비어있을 수 없습니다.");
    }

    @Test
    @DisplayName("시작일자가 현재시간보다 이전이라면 생성시 오류가 발생한다.")
    void createSessionDuration_startDate_beforeNow() {
        LocalDateTime startDate = LocalDateTime.now();
        assertThatThrownBy(() -> new SessionDuration(startDate.minusDays(1), startDate.plusHours(1)))
                .isInstanceOf(InvalidValueException.class)
                .hasMessage("시작날짜는 현재시간보다 이전일 수 없습니다.");
    }

    @Test
    @DisplayName("시작일자가 종료일자보다 이후라면 생성시 오류가 발생한다.")
    void createSessionDuration_startDate_after_endDate() {
        LocalDateTime startDate = LocalDateTime.now();
        assertThatThrownBy(() -> new SessionDuration(startDate.plusHours(1), startDate))
                .isInstanceOf(InvalidValueException.class)
                .hasMessage("시작날짜는 종료날짜보다 이후일 수 없습니다.");
    }

    @Test
    @DisplayName("시작일자와 종료일자가 동일하면 생성시 오류가 발생한다.")
    void createSessionDuration_startDate_same_endDate() {
        LocalDateTime startDate = LocalDateTime.now();
        assertThatThrownBy(() -> new SessionDuration(startDate.plusDays(1), startDate.plusDays(1)))
                .isInstanceOf(InvalidValueException.class)
                .hasMessage("시작날짜와 종료날짜는 동일할 수 없습니다.");
    }
}
