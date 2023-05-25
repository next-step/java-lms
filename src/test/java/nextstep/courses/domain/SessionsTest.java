package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SessionsTest {
    @Test
    @DisplayName("동일한 강의를 등록 시, IllegalArgumentException 예외 발생")
    void add_duplicate_session_then_throw_IllegalArgumentException() {
        // given
        Sessions sessions = new Sessions();
        Session session = SessionFixture.TDD_SESSION.session();

        sessions.add(session);

        // when, then
        assertThatThrownBy(() -> sessions.add(session))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 등록된 강의입니다: " + session.name());
    }
}