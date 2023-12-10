package nextstep.Session;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionDurationTest {

    @Test
    @DisplayName("강의 시작일은 종료일보다 작을 수 없습니다.")
    void sessionDurationTest() {
        LocalDateTime start = LocalDateTime.parse("2023-12-02T00:00");
        LocalDateTime end = LocalDateTime.parse("2023-12-01T00:00");

        Assertions.assertThatIllegalArgumentException()
            .isThrownBy(() -> new SessionDuration(start, end));
    }

}