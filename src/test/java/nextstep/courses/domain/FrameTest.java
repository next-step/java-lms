package nextstep.courses.domain;

import nextstep.courses.domain.image.Frame;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FrameTest {

    @Test
    void is_appropriate_ratio() {
        assertThatCode(() -> new Frame(300, 200)).doesNotThrowAnyException();
    }

    @Test
    void is_not_appropriate_ratio() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Frame(350, 200));
    }
}