package nextstep.courses.domain;

import nextstep.courses.domain.utils.TestSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SessionsTest {

    private final Long sessionId1 = 1L;
    private final Long sessionId2 = 2L;

    @Test
    @DisplayName("강의 목록 생성 테스트")
    void testSessions() {
        Sessions sessions = new Sessions();
        sessions.addSession(TestSessionFactory.of(sessionId1));
        sessions.addSession(TestSessionFactory.of(sessionId2));

        assertThat(sessions.getSessions()).hasSize(2).containsExactly(TestSessionFactory.of(sessionId1), TestSessionFactory.of(sessionId2));
    }

    @Test
    @DisplayName("중복된 강의가 등록될 경우 에러 발생")
    void testDuplicateSession() {
        Sessions sessions = new Sessions();
        sessions.addSession(TestSessionFactory.of(sessionId1));

        assertThatIllegalArgumentException().isThrownBy(() -> sessions.addSession(TestSessionFactory.of(sessionId1)));
    }
}
