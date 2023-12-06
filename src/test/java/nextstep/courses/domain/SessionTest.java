package nextstep.courses.domain;

import nextstep.courses.exception.SessionException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    @DisplayName("무료 강의는 최대 수강 인원 제한 없이 수강 신청을 할 수 있다.")
    void 무료강의_수강신청() {
        Session session = new Session(0L, SessionType.FREE, 10, new ArrayList<>(), 15);
        session.enrollStudent(NsUserTest.JAVAJIGI);

        assertThat(session.equals(new Session(0L, SessionType.FREE, 10, List.of(NsUserTest.JAVAJIGI), 16))).isTrue();
    }

    @Test
    @DisplayName("유료강의는 최대 수강 인원을 초과하면 예외가 발생한다.")
    void 유료강의_수강신청() {
        Session session = new Session(0L, SessionType.PAID, 10, new ArrayList<>(), 10);

        assertThatThrownBy(() -> session.enrollStudent(NsUserTest.JAVAJIGI))
                .isInstanceOf(SessionException.class);
    }
}