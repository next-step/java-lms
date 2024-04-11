package nextstep.courses.domain.session.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.image.ImageFileSize.INVALID_IMAGE_FILE_SIZE;
import static nextstep.courses.domain.session.image.ImageSize.*;
import static nextstep.courses.domain.session.image.ImageTypeEnum.INVALID_IMAGE_TYPE;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CoverImageTest {

    @Test
    @DisplayName("커버 이미지 객체 생성")
    void testCoverImage_of() {
        // given
        String imageType = "jpg";
        int imageFileSize = 1024;
        int imageWidth = 300;
        int imageHeight = 200;

        // when
        CoverImage coverImage = CoverImage.of(imageType, imageFileSize, imageWidth, imageHeight);

        // then
        assertAll(
                "Cover Image is correctly set",
                () -> assertEquals(coverImage.getImageType(), imageType),
                () -> assertEquals(coverImage.getImageFileSize(), imageFileSize),
                () -> assertEquals(coverImage.getImageSizeWidth(), imageWidth),
                () -> assertEquals(coverImage.getImageSizeHeight(), imageHeight)
        );
    }

    @Test
    @DisplayName("커버 이미지 객체 생성 실패 - 유효하지 않은 이미지 파일 타입")
    void testCoverImage_invalidImageType_ShouldThrowException() {
        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            CoverImage.of("exe", 1024, 300, 200);
        }).withMessageContaining(INVALID_IMAGE_TYPE);
    }

    @Test
    @DisplayName("커버 이미지 객체 생성 실패 - 유효하지 이미지 파일 크기")
    void testCoverImage_invalidImageSize_ShouldThrowException() {
        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            CoverImage.of("jpg", 2000, 300, 200);
        }).withMessageContaining(INVALID_IMAGE_FILE_SIZE);
    }

    @Test
    @DisplayName("커버 이미지 객체 생성 실패 - 유효하지 않은 이미지 사이즈 넓이")
    void testCoverImage_invalidImageWidth_ShouldThrowException() {
        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            CoverImage.of("jpg", 1024, 200, 200);
        }).withMessageContaining(INVALID_WIDTH);
    }

    @Test
    @DisplayName("커버 이미지 객체 생성 실패 - 유효하지 않은 이미지 사이즈 높이")
    void testCoverImage_invalidImageHeight_ShouldThrowException() {
        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            CoverImage.of("jpg", 1024, 300, 100);
        }).withMessageContaining(INVALID_HEIGHT);
    }


    @Test
    @DisplayName("커버 이미지 객체 생성 실패 - 유효하지 이미지 사이즈 사이즈 비율")
    void testCoverImage_invalidImageSizeRatio_ShouldThrowException() {
        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            CoverImage.of("jpg", 1024, 600, 500);
        }).withMessageContaining(INVALID_IMAGE_RATIO);
    }

}
