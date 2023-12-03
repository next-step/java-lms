package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SessionTest {

    @DisplayName("강의는 시작일과 종료일을 가지며, 시작일이 종료일 이후일 수 없다.")
    @Test
    void 강의_시작종료일() {
        LocalDateTime date1 = LocalDateTime.of(2023, 12, 1, 0, 0, 0);
        LocalDateTime date2 = LocalDateTime.of(2023, 12, 10, 0, 0, 0);
        Session session = new Session(0L, 0L, date1, date2, null);
        String sessionPeriod = session.period();

        assertThat(sessionPeriod).isEqualTo("2023-12-01T00:00~2023-12-10T00:00");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Session(0L, 0L, date2, date1, null));
    }


}