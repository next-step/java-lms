package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionStatusTest {
    @Test
    @DisplayName("of 메서드의 입력으로 RECRUITING이 들어오면, SessionStatus.ONGOING을 반환한다.")
    void testOf() {
        //given
        String statusName = "RECRUITING";

        //when
        SessionStatus sessionStatus = SessionStatus.of(statusName);

        //then
        assertThat(sessionStatus).isEqualTo(SessionStatus.ONGOING);
    }
}
