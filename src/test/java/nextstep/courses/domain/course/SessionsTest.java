package nextstep.courses.domain.course;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionTest;

class SessionsTest {

    @Test
    @DisplayName("이미 존재하는 강의를 재추가하는 경우 예외를 던진다.")
    void Add_ExistedSession_Exception() {
        final Sessions sessions = new Sessions();
        final Session session = SessionTest.session();
        sessions.add(session);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> sessions.add(session));
    }
}
