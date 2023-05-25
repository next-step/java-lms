package nextstep.courses.domain;

import nextstep.courses.RegisterCourseException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class SessionsTest {
    @Test
    @DisplayName("해당하는 강의 id 가 없을 경우 exception")
    void registerSession_notExistId() {
        final Sessions sessions = new Sessions(
                List.of(
                        new Session(1L, LocalDateTime.now(), null,
                                new SessionInfo(10, 0, SessionStatusType.IN_PROGRESS),
                                new CoverImage("url"),
                                new SessionDate(LocalDateTime.now(), LocalDateTime.now()),
                                new Price(1000))));

        Assertions.assertThatThrownBy(() -> sessions.registerSession(2L, 3))
                .isInstanceOf(RegisterCourseException.class);
    }
}
