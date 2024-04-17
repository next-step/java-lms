package nextstep.courses.domain;

import nextstep.courses.ImageException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageTest {

    @Test
    void 이미지_생성() throws ImageException {
        Image image = Image.createImage(500_000, 300, 200, "jpg");
        assertThat(image).isNotNull();
    }

    @Test
    void 부적절한_이미지타입() {
        assertThatThrownBy(() -> Image.createImage(500_000, 300, 200, "psd"))
                .isInstanceOf(ImageException.class);
    }

    @Test
    void 부적절한_이미지크기() {
        assertThatThrownBy(() -> Image.createImage(1_000_000, 300, 200, "jpg"))
                .isInstanceOf(ImageException.class);
    }

    @Test
    void 부적절한_이미지_너비_크기() {
        assertThatThrownBy(() -> Image.createImage(500_000, 450, 300, "jpg"))
                .isInstanceOf(ImageException.class);
    }

    @Test
    void 부적절한_이미지_비율() {
        assertThatThrownBy(() -> Image.createImage(500_000, 300, 100, "jpg"))
                .isInstanceOf(ImageException.class);
    }
}
