package nextstep.courses.domain.coverImage;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.domain.coverimage.CoverImage;
import org.junit.jupiter.api.Test;

class CoverImageTest {

    @Test
    void 이미지_타입_검증() {
        String wrongImageType = "pdf";
        int imageCapacity = 1024;
        int width = 300;
        int height = 200;

        assertThatThrownBy(() -> new CoverImage(wrongImageType, imageCapacity, width, height)).isInstanceOf(
                IllegalArgumentException.class)
            .hasMessage("강의 커버 이미지는 [gif, jpg, jpeg, png, svg] 타입만 가능합니다.");
    }

    @Test
    void 이미지_용량_검증() {
        String imageType = "png";
        int wrongImageCapacity = 2048;
        int width = 300;
        int height = 200;

        assertThatThrownBy(() -> new CoverImage(imageType, wrongImageCapacity, width, height)).isInstanceOf(
                IllegalArgumentException.class)
            .hasMessage("강의 커버 이미지는 1024 이하여야 합니다.");
    }

    @Test
    void 이미지_가로_검증() {
        String imageType = "png";
        int imageCapacity = 1024;
        int wrongWidth = 200;
        int height = 200;

        assertThatThrownBy(() -> new CoverImage(imageType, imageCapacity, wrongWidth, height)).isInstanceOf(
                IllegalArgumentException.class)
            .hasMessage("이미지의 width는 300 픽셀 이상이어야 합니다.");
    }

    @Test
    void 이미지_세로_검증() {
        String imageType = "png";
        int imageCapacity = 1024;
        int width = 300;
        int wrongHeight = 100;

        assertThatThrownBy(() -> new CoverImage(imageType, imageCapacity, width, wrongHeight)).isInstanceOf(
                IllegalArgumentException.class)
            .hasMessage("이미지의 height는 200 이상이어야 합니다.");
    }

    @Test
    void 이미지_해상도_검증() {
        String imageType = "png";
        int imageCapacity = 1024;
        int wrongWidth = 400;
        int wrongHeight = 300;

        assertThatThrownBy(() -> new CoverImage(imageType, imageCapacity, wrongWidth, wrongHeight)).isInstanceOf(
                IllegalArgumentException.class)
            .hasMessage("이미지의 해상도는 1.5 이어야 합니다.");
    }
}
