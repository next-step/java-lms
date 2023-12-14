package nextstep.courses.type;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class MaxRegisterTest {
    @Test
    @DisplayName("[MaxRegister.of] 음수 불가능")
    public void negativeTest() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> {
                    MaxRegister.of(-1);
                });
    }

    @Test
    @DisplayName("[MaxRegister.isLessThan] 유한 lessThan 테스트")
    public void finiteLessThan() {
        assertThat(MaxRegister.of(10).isLessThan(11)).isTrue();
        assertThat(MaxRegister.of(10).isLessThan(10)).isFalse();
    }

    @Test
    @DisplayName("[MaxRegister.isLessThan] 무한 lessThan 테스트. 무한은 그 어떤 수보다도 크기 때문에 false 반환")
    public void infiniteLessThan() {
        assertThat(MaxRegister.infinite().isLessThan(Integer.MAX_VALUE)).isFalse();
    }

    @Test
    @DisplayName("[MaxRegister.isLargerThan] 유한 largerThan 테스트")
    public void finiteLargerThan() {
        assertThat(MaxRegister.of(10).isLargerThan(9)).isTrue();
        assertThat(MaxRegister.of(10).isLargerThan(10)).isFalse();
    }

    @Test
    @DisplayName("[MaxRegister.isLargerThan] 무한 largerThan 테스트. 무한은 그 어떤 수보다도 크기 때문에 true 반환")
    public void infiniteLargerThan() {
        assertThat(MaxRegister.infinite().isLargerThan(Integer.MAX_VALUE)).isTrue();
    }
}
