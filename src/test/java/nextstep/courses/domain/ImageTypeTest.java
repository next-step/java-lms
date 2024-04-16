package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ImageTypeTest {
    @Test
    @DisplayName("image type을 확인한다.")
    void check_image_type_valid() {
        assertThat(ImageType.isValid(ImageType.JPEG)).isTrue();
    }
}
