package nextstep.payments.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AmountTest {
    @Nested
    @DisplayName("Amount 인스턴스 생성 테스트")
    class InstanceCreationTest {
        @Test
        @DisplayName("인스턴스 생성시 Null 또는 음수가 전달된 경우 IllegalArgumentException이 발생한다.")
        void testFailCase() {
            assertThatThrownBy(() -> new Amount(null)).isExactlyInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> new Amount(-1L)).isExactlyInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("검증 조건을 문제 없이 통과한 경우 어떠한 예외도 발생하지 않고 인스턴스가 생성된다.")
        void testSuccessCase() {
            assertThatNoException().isThrownBy(Amount::new);
            assertThatNoException().isThrownBy(() -> new Amount(100L));
        }
    }
}