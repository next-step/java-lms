package nextstep.courses.domain.session;

import nextstep.courses.exception.IncorrectDurationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionDurationTest {

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    @BeforeEach
    void setUp() {
        startedAt = LocalDateTime.of(2023, 4, 3, 0, 0);
        endedAt = LocalDateTime.of(2023, 6, 1, 0, 0);
    }

    @Test
    @DisplayName("시작일은 종료일보다 앞에 있어야 한다.")
    void test01() {
        assertThatNoException().isThrownBy(() -> new SessionDuration(startedAt, endedAt));
    }

    @Test
    @DisplayName("시작일은 종료일보다 앞에 있지 않으면 예외가 발생한다.")
    void test02() {
        assertThatThrownBy(() -> new SessionDuration(endedAt, startedAt))
                .isInstanceOf(IncorrectDurationException.class);
    }

}
