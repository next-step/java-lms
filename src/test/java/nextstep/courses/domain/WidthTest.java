package nextstep.courses.domain;

import nextstep.courses.domain.image.Width;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class WidthTest {

    @Test
    void is_over_300() {
        assertThatCode(() -> new Width(300)).doesNotThrowAnyException();
        assertThatIllegalArgumentException().isThrownBy(() -> new Width(200));
    }
}