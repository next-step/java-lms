package nextstep.courses.domain.cover;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoverImageTest {

    @DisplayName("유효한 이미지 정보를 사용하여 CoverImage 객체 생성")
    @Test
    void createValidCoverImage() {
        int imageSize = 500 * 1024;
        String extension = "jpg";
        int width = 300;
        int height = 200;

        CoverImage coverImage = CoverImage.of(imageSize, extension, width, height);

        assertThat(coverImage).isNotNull();
        assertThat(coverImage.getWidth()).isEqualTo(width);
        assertThat(coverImage.getHeight()).isEqualTo(height);
        assertThat(coverImage.getImageSize()).isEqualTo(imageSize);
        assertThat(coverImage.getExtension().getText()).isEqualTo(extension.toLowerCase());
    }

    @DisplayName("허용되지 않는 이미지 확장자 사용 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"bmp", "tiff", "pdf", "txt", "exe"})
    void throwExceptionForInvalidImageExtension(String extension) {
        int imageSize = 500 * 1024; // 500KB
        int width = 300;
        int height = 200;

        assertThatThrownBy(() -> CoverImage.of(imageSize, extension, width, height))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("허용되지 않는 이미지 형식입니다.");
    }

    @DisplayName("이미지 크기가 1MB를 초과할 경우 예외가 발생한다.")
    @Test
    void throwExceptionWhenImageSizeExceedsLimit() {
        int imageSize = 2 * 1024 * 1024;
        String extension = "jpg";
        int width = 300;
        int height = 200;

        assertThatThrownBy(() -> CoverImage.of(imageSize, extension, width, height))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 크기는 1MB 이하여야 합니다.");
    }

    @DisplayName("이미지의 크기 또는 비율이 유효하지 않을 때 예외가 발생한다.")
    @Test
    void throwExceptionWhenDimensionsAreInvalid() {
        int imageSize = 500 * 1024;
        String extension = "jpg";

        assertThatThrownBy(() -> CoverImage.of(imageSize, extension, 250, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지의 크기와 비율이 유효하지 않습니다.");

        assertThatThrownBy(() -> CoverImage.of(imageSize, extension, 300, 150))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지의 크기와 비율이 유효하지 않습니다.");

        assertThatThrownBy(() -> CoverImage.of(imageSize, extension, 400, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지의 크기와 비율이 유효하지 않습니다.");
    }
}
