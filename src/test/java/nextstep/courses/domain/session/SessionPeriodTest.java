package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class SessionPeriodTest {

    @Test
    void 강의_종료일이_강의_시작일보다_전이라면_예외가_발생한다() {
        assertThatThrownBy(() -> new SessionPeriod(LocalDate.now(), LocalDate.now().minusDays(1)))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("강의 시작일은 강의 종료일 이전이어야 합니다.");
    }
}
