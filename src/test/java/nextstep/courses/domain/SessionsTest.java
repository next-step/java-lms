package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SessionsTest {

    private final Image image = new Image(500f, "png", 600, 400);

    private Session createSessionWithId(int id) {
        return new Session(
                "Test",
                id,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                0,
                0,
                0,
                image,
                SessionStatus.RECRUITING,
                new FreeJoinStrategy()
        );
    }

    @Test
    @DisplayName("특정 ID의 세션을 찾을 수 있다")
    void findById_success() {
        Session s1 = createSessionWithId(1);
        Session s2 = createSessionWithId(2);

        Sessions sessions = new Sessions(List.of(s1, s2));

        Session found = sessions.findById(2);

        assertThat(found).isEqualTo(s2);
    }

    @Test
    @DisplayName("존재하지 않는 ID로 세션을 찾으면 예외가 발생한다")
    void findById_fail_throwsException() {
        Session s1 = createSessionWithId(1);
        Sessions sessions = new Sessions(List.of(s1));

        assertThatThrownBy(() -> sessions.findById(99))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 ID의 세션이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("Sessions 리스트는 외부에서 수정할 수 없다")
    void sessions_shouldBeImmutable() {
        Session s1 = createSessionWithId(1);
        Sessions sessions = new Sessions(List.of(s1));

        List<Session> list = sessions.sessions();

        assertThatThrownBy(() -> list.add(createSessionWithId(2)))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
