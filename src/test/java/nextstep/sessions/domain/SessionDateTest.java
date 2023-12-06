package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class SessionDateTest {
    public static final SessionDate NOV = new SessionDate(LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 30));
    public static final SessionDate DEC = new SessionDate(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31));

    @DisplayName("시작일과 종료일을 전달하면 SessionDate 객체를 생성한다.")
    @Test
    void sessionDateTest() {
        assertThat(new SessionDate(LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 15))).isInstanceOf(SessionDate.class);
    }

    @DisplayName("종료일이 시작일 전이면 IllegalStateException을 던진다.")
    @Test
    void sessionDateExceptionTest() {
        assertThatThrownBy(() -> new SessionDate(LocalDate.of(2023, 11, 2), LocalDate.of(2023, 11, 1)))
                .isInstanceOf(IllegalStateException.class);
    }
}
