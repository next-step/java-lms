package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PeriodTest {
    @Nested
    @DisplayName("Period 생성 테스트")
    class InstanceCreationTest {
        @Test
        @DisplayName("startedAt <= endedAt이 아닌 경우 IllegalArgumentException이 발생한다.")
        void testFailCase() {
            LocalDateTime startedAt = LocalDateTime.of(2024, 4, 10, 18, 0);
            LocalDateTime endedAt = LocalDateTime.of(2024, 4, 9, 18, 0);

            assertThatThrownBy(() -> new Period(startedAt, endedAt)).isExactlyInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("검증 조건을 모두 만족하는 경우 어떠한 예외도 발생하지 않고 인스턴스가 생성된다.")
        void testSuccessCase() {
            LocalDateTime startedAt = LocalDateTime.of(2024, 4, 10, 18, 0);
            LocalDateTime endedAt = LocalDateTime.of(2024, 6, 5, 18, 0);

            assertThatNoException().isThrownBy(() -> new Period(startedAt, endedAt));
        }
    }
}