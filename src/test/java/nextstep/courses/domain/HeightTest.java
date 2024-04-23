package nextstep.courses.domain;

import nextstep.courses.domain.image.Height;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class HeightTest {

    @Test
    void is_over_200() {
        assertThatCode(() -> new Height(200)).doesNotThrowAnyException();
        assertThatIllegalArgumentException().isThrownBy(() -> new Height(100));
    }
}