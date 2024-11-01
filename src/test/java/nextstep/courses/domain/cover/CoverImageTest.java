package nextstep.courses.domain.cover;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CoverImageTest {

    @DisplayName("유효한 이미지 정보를 사용하여 CoverImage 객체 생성")
    @Test
    void createValidCoverImage() {
        int imageSize = 500 * 1024;
        String extension = "jpg";
        int width = 300;
        int height = 200;

        CoverImage coverImage = CoverImage.of(ImageSize.of(imageSize), extension, ImageDimension.of(width, height));

        assertAll(
                () -> assertNotNull(coverImage),
                () -> assertEquals(width, coverImage.getWidth()),
                () -> assertEquals(height, coverImage.getHeight()),
                () -> assertEquals(imageSize, coverImage.getImageSize()),
                () -> assertEquals(extension.toLowerCase(), coverImage.getExtension().getText())
        );
    }

    @DisplayName("허용되지 않는 이미지 확장자 사용 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"bmp", "tiff", "pdf", "txt", "exe"})
    void throwExceptionForInvalidImageExtension(String extension) {
        int imageSize = 500 * 1024; // 500KB
        int width = 300;
        int height = 200;

        assertThatThrownBy(
                () -> CoverImage.of(ImageSize.of(imageSize), extension, ImageDimension.of(width, height))
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("허용되지 않는 이미지 형식입니다.");
    }

}
