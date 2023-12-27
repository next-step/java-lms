package nextstep.courses.domain.course.session;

import nextstep.courses.fixture.SessionFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionsTest {
    @ParameterizedTest
    @NullSource
    @DisplayName("Sessions 은 빈 값이 주어지면 예외를 던진다.")
    void newObject_null_throwsException(List<Session> sessions) {
        assertThatThrownBy(
                () -> new Sessions(sessions)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("add 는 이미 강의가 추가 되었으면 예외를 던진다.")
    void add_alreadyExistedSession_throwsException() {
        List<Session> sessionList = new ArrayList<>();
        sessionList.add(SessionFixtures.createdChargedSession());
        Sessions sessions = new Sessions(sessionList);

        assertThatThrownBy(
                () -> sessions.add(SessionFixtures.createdChargedSession())
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
