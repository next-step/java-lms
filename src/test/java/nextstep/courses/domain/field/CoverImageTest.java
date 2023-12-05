package nextstep.courses.domain.field;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CoverImageTest {

    @Test
    public void assertVerifyingImageSize() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CoverImage coverImage = new CoverImage(2L, 350, 250, ImageType.GIF);
        });

        assertThat(exception.getMessage()).isEqualTo("이미지 크기는 1MB 이하만 가능합니다.");
    }

    @Test
    public void assertVerifyingImageWidth() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CoverImage coverImage = new CoverImage(0.9, 250, 250, ImageType.GIF);
        });

        assertThat(exception.getMessage()).isEqualTo("이미지의 width는 300픽셀, height는 200픽셀 이상만 가능합니다.");
    }

    @Test
    public void assertVerifyingImageHeight() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CoverImage coverImage = new CoverImage(0.9, 350, 150, ImageType.GIF);
        });

        assertThat(exception.getMessage()).isEqualTo("이미지의 width는 300픽셀, height는 200픽셀 이상만 가능합니다.");
    }

    @Test
    public void assertVerifyingImageType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CoverImage coverImage = new CoverImage(0.9, 350, 250, ImageType.of("WRONG TYPE"));
        });

        assertThat(exception.getMessage()).isEqualTo("옳지 않은 확장자입니다.");
    }

}