package nextstep.courses.domain;

import nextstep.courses.domain.image.Size;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SizeTest {

    @Test
    void is_size_under_1MB() {
        assertThatCode(() -> new Size(400)).doesNotThrowAnyException();
        assertThatIllegalArgumentException().isThrownBy(() -> new Size(2048));
    }
}