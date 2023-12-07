package nextstep.courses.domain.course.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionsTest {
    @ParameterizedTest
    @NullSource
    @DisplayName("Sessions 은 빈 값이 주어지면 예외를 던진다.")
    void newObject_empty_throwsException(List<Session> sessions) {
        assertThatThrownBy(
                () -> new Sessions(sessions)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
