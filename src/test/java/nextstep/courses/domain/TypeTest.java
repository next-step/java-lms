package nextstep.courses.domain;

import nextstep.courses.domain.image.Type;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.image.Type.*;

public class TypeTest {

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
