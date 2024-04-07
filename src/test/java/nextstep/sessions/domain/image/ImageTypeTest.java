package nextstep.sessions.domain.image;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static nextstep.sessions.domain.image.ImageType.*;

public class ImageTypeTest {

    @Test
    void SVG() {
        Assertions.assertThat(SVG == SVG).isTrue();
    }

    @Test
    void PNG() {
        Assertions.assertThat(PNG == PNG).isTrue();
    }

    @Test
    void JPEG() {
        Assertions.assertThat(JPEG == JPEG).isTrue();
    }

    @Test
    void JPG() {
        Assertions.assertThat(JPG == JPG).isTrue();
    }

    @Test
    void GIF() {
        Assertions.assertThat(GIF == GIF).isTrue();
    }
}
