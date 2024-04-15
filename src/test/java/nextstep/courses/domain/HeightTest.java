package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class HeightTest {

    @Test
    void is_over_200() {
        assertThatCode(() -> new Height(200)).doesNotThrowAnyException();
        assertThatIllegalArgumentException().isThrownBy(() -> new Height(100));
    }
}