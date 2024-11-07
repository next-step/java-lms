package nextstep.courses.domain.cover;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CoverImageTest {

    @DisplayName("유효한 이미지 정보를 사용하여 CoverImage 객체 생성")
    @Test
    void createValidCoverImage() {
        String fileName = "effective java";
        int imageSize = 500 * 1024;
        String extension = "jpg";
        int width = 300;
        int height = 200;

        CoverImage coverImage = CoverImage.of(fileName,ImageSize.of(imageSize), extension, ImageDimension.of(width, height));

        assertAll(
                () -> assertThat(coverImage).isNotNull(),
                () -> assertThat(fileName).isEqualTo(coverImage.getFileName()),
                () -> assertThat(width).isEqualTo(coverImage.getWidth()),
                () -> assertThat(height).isEqualTo(coverImage.getHeight()),
                () -> assertThat(imageSize).isEqualTo(coverImage.getImageSize()),
                () -> assertThat(extension.toLowerCase()).isEqualTo(coverImage.getExtension().getText())
        );
    }

}
