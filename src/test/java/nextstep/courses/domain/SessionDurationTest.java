package nextstep.courses.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SessionDurationTest {

    @Test
    void 시작일은_등록일보다_늦으면_예외() {
        LocalDateTime startDate = LocalDateTime.of(2024, 4, 25, 10, 30);
        LocalDateTime endDate = LocalDateTime.of(2024, 4, 25, 10, 29);
        Assertions.assertThatThrownBy(() -> new SessionDuration(
                startDate,
                endDate
        )).isInstanceOf(IllegalArgumentException.class);
    }
}
