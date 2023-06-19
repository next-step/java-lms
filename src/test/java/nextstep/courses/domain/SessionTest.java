package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    public static final Session tDDJavaSession = new Session(1L,
            Status.RECRUITING,
            new SessionInfo("JAVA_TDD", "image_url"),
            LocalDateTime.now(),
                    LocalDateTime.now(),
                    10);
    @Test
    @DisplayName("강의 모집중 상태를 변경 할 수 있다.")
    public void sessionStatusExceptionTest() throws CannotEnrollException {
        tDDJavaSession.changeStatue(Status.WAITING);
        assertThatThrownBy(tDDJavaSession::checkAbleSession)
            .isInstanceOf(CannotEnrollException.class);

    }

}
