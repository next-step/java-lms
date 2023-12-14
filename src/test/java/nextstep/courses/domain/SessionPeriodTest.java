package nextstep.courses.domain;

import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.SessionState;
import nextstep.courses.exception.SessionPeriodException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionPeriodTest {

    @Test
    @DisplayName("오늘 날짜가 강의 시작일 이전이 아닌데 준비중 상태를 가지면 예외를 발생한다.")
    void 오늘_날짜가_강의_시작일_이전이_아니면_준비중_상태_예외_발생() {
        assertThatThrownBy(() -> new SessionPeriod(LocalDate.now().minusDays(1), LocalDate.now().plusDays(3), SessionState.PREPARING))
                .isInstanceOf(SessionPeriodException.class);
    }

    @Test
    @DisplayName("오늘 날짜가 강의 시작일 이전이 아닌데 모집중 상태를 가지면 예외를 발생한다.")
    void 오늘_날짜가_강의_시작일_이전이_아니면_모집중_상태_예외_발생() {
        assertThatThrownBy(() -> new SessionPeriod(LocalDate.now().minusDays(1), LocalDate.now().plusDays(3), SessionState.RECRUITING))
                .isInstanceOf(SessionPeriodException.class);
    }

    @Test
    @DisplayName("오늘 날짜가 강의 종료일 이후가 아닌데 종료 상태를 가지면 예외를 발생한다.")
    void 오늘_날짜가_강의_종료일_이후가_아니면_종료_상태_예외_발생() {
        assertThatThrownBy(() -> new SessionPeriod(LocalDate.now().plusDays(3), LocalDate.now().plusDays(10), SessionState.END))
                .isInstanceOf(SessionPeriodException.class);
    }
}