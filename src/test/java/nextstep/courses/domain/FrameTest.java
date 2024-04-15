package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    @Test
    void is_width_over_300() {
        assertThatCode(() -> new Frame(300, 200)).doesNotThrowAnyException();
        assertThatIllegalArgumentException().isThrownBy(() -> new Frame(350, 200));
    }
}