package nextstep.courses.domain.image;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageTypeTest {

    @ParameterizedTest
    @ValueSource(strings = {"jpg", "jpeg", "gif", "svg", "png"})
    public void 허용타입_허용(String imageType) {
        Assertions.assertThat(ImageType.from(imageType)).isNotNull();
    }

    @Test
    public void 지원하지_않는_타입이면_예외() {
        assertThatThrownBy(() -> ImageType.from("bmp"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("지원하지 않는 이미지 타입");
    }
}
