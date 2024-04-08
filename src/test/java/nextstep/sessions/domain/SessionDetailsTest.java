package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.sessions.domain.SessionStatus.END;
import static nextstep.sessions.domain.SessionStatus.RECRUITING;
import static nextstep.sessions.domain.SessionType.PAID;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionDetailsTest {

    @DisplayName("유료강의는 수강신청을 했을 때, 최대 수강 인원을 초과하면 예외를 반환한다")
    @Test
    void greaterThanMax() {
        SessionDetails details = new SessionDetails(40, 40, 30000, PAID, RECRUITING);

        assertThatThrownBy(details::register)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("강의가 모집중이 아닐때 수강신청을 하면 예외를 반환한다")
    @Test
    void statusIsNotRecruiting() {
        SessionStatus end = END;
        SessionDetails details = new SessionDetails(39, 40, 30000, PAID, end);

        assertThatThrownBy(details::register)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("현재 강의는 (%s)인 상태입니다.", end));
    }

    //@DisplayName("무료강의는 수강신청을 언제든 할 수 있다.")

}
