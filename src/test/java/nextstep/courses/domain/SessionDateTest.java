package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SessionDateTest {

    private static final LocalDate startAt = LocalDate.of(2024, 4, 1);
    private static final LocalDate endAt = LocalDate.of(2024, 4, 30);
    private static final LocalDate wrongEndAt = LocalDate.of(2024, 3, 31);

    @Test
    void 생성() {
        assertThat(new SessionDate(startAt, endAt)).isEqualTo(new SessionDate(startAt, endAt));
    }

    @Test
    void 잘못된_종료일자() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new SessionDate(startAt, wrongEndAt))
                .withMessageMatching("종료일자가 시작일자보다 빠릅니다");
    }
}
