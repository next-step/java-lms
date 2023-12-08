package nextstep.courses.type;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CapacityTest {
    @Test
    @DisplayName("[Capacity.new] 파일 용량은 음수일 수 없음")
    public void negativeTest() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> {
                    new Capacity(-1);
                });
    }

    @Test
    @DisplayName("[Capacity.throwIfCapacityIsLagerThan] 주어진 숫자보다 용량이 크면 -> 예외 던짐")
    public void throwIfCapacityIsLagerThanTest() {
        Capacity capacity = new Capacity(50);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> {
                    capacity.throwIfCapacityIsLagerThan(new Capacity(49));
                });

        assertThatCode(() -> {
            capacity.throwIfCapacityIsLagerThan(new Capacity(50));
        })
        .doesNotThrowAnyException();
    }
}