package nextstep.session.type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ImageExtensionTypeTest {

    @DisplayName("Type에 대해 .extension으로 받는다.")
    @Test
    void getExtension() {
        // then
        assertThat(ImageExtensionType.JPG.getExtension())
                .isEqualTo(".jpg");
    }
}
