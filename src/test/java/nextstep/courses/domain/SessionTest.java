package nextstep.courses.domain;

import nextstep.courses.exception.SessionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    @Test
    @DisplayName("오늘 날짜가 강의 시작일 이전이 아닌데 준비중 상태를 가지면 예외를 발생한다.")
    void 오늘_날짜가_강의_시작일_이전이_아니면_준비중_상태_예외_발생() {
        assertThatThrownBy(() -> new Session(0L, SessionState.PREPARING, LocalDate.now().minusDays(1), LocalDate.now().plusDays(5)))
                .isInstanceOf(SessionException.class);
    }

    @Test
    @DisplayName("오늘 날짜가 강의 시작일 이전이 아닌데 모집중 상태를 가지면 예외를 발생한다.")
    void 오늘_날짜가_강의_시작일_이전이_아니면_모집중_상태_예외_발생() {
        assertThatThrownBy(() -> new Session(0L, SessionState.RECRUITING, LocalDate.now().minusDays(1), LocalDate.now().plusDays(5)))
                .isInstanceOf(SessionException.class);
    }

    @Test
    @DisplayName("오늘 날짜가 강의 종료일 이후가 아닌데 종료 상태를 가지면 예외를 발생한다.")
    void 오늘_날짜가_강의_종료일_이후가_아니면_종료_상태_예외_발생() {
        assertThatThrownBy(() -> new Session(0L, SessionState.END, LocalDate.now().minusDays(10), LocalDate.now().plusDays(5)))
                .isInstanceOf(SessionException.class);
    }

}