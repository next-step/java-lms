package nextstep.sessions.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class SessionPeriodTest {
    private LocalDate earlier;
    private LocalDate later;

    @BeforeEach
    void setUp() {
        this.earlier = LocalDate.of(2025, 1, 1);
        this.later = LocalDate.of(2025, 3, 1);
    }

    @Test
    @DisplayName("시작일이 종료일보다 이전이면 기간을 정상 생성한다.")
    void 기간_정상_생성() {
        SessionPeriod sessionPeriod = new SessionPeriod(earlier, later);
    }

    @Test
    @DisplayName("시작일이 종료일보다 늦으면 IllegalArgumentException을 던진다.")
    void 시작일이_종료일보다_늦으면_예외발생() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(()
                -> new SessionPeriod(later, earlier))
                .withMessage("시작일은 종료일보다 이전이어야 합니다.");

    }
}
