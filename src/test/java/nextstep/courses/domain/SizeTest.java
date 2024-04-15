package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class SizeTest {

    @Test
    void is_size_under_1MB() {
        assertThatCode(() -> new Size(400)).doesNotThrowAnyException();
        assertThatIllegalArgumentException().isThrownBy(() -> new Size(2048));
    }
}