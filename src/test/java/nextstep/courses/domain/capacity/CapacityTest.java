package nextstep.courses.domain.capacity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class CapacityTest {
    @Test
    public void create() {
        assertThat(new Capacity("1000")).isEqualTo(new Capacity(1000));
    }

    @Test
    public void exceedCapacity() {
        assertThat(new Capacity(1000).exceed(new Capacity(1))).isTrue();
    }
}
