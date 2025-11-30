package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    @Test
    public void 정상적인_강의_생성() {
        LocalDate startDate = LocalDate.of(2025, 11, 3);
        LocalDate endDate = LocalDate.of(2025, 12, 18);
        SessionImage image = new SessionImage(500_000L, "png", 300, 200);

        Session session = new Session(startDate, endDate, image);

        assertThat(session).isNotNull();
    }

    @Test
    public void 종료일이_시작일보다_이전이면_예외() {
        LocalDate startDate = LocalDate.of(2026, 11, 3);
        LocalDate endDate = LocalDate.of(2025, 12, 18);
        SessionImage image = new SessionImage(500_000L, "png", 900, 600);

        assertThatThrownBy(() -> new Session(startDate, endDate, image))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("종료일은 시작일보다 이후여야 한다");
    }
}
