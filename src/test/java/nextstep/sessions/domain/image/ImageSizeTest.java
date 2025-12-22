package nextstep.sessions.domain.image;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class ImageSizeTest {

    @Test
    void whenImageSizeExceeds1MB_thenThrow() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new ImageSize(1_048_577)
        ).withMessageContaining("1MB");
    }

}