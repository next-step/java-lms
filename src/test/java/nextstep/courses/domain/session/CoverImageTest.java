package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.CoverImage.FILE_SIZE_LIMIT_1MB;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoverImageTest {
    @Test
    @DisplayName("CoverImage 생성 테스트")
    void testCoverImageConstructor() {
        //given, when
        final CoverImage coverImage = new CoverImage(1000, ImageType.JPG, 300, 200);

        //then
        assertThat(coverImage).isNotNull();
    }

    @Test
    @DisplayName("CoverImage::defaultCoverImage를 사용하면 크기 1KB, 타입 : JPG, 너비 : 300, 높이 : 200 을 가진 CoverImage를 생성한다.")
    void testDefaultCoverImage() {
        //given
        final CoverImage expectedCoverImage = new CoverImage(1000, ImageType.JPG, 300, 200);

        //when
        final CoverImage coverImage = CoverImage.defaultCoverImage();

        //then
        assertThat(coverImage).isEqualTo(expectedCoverImage);
    }

    @Test
    @DisplayName("용량이 1MB를 넘어가면, 예외가 발생한다.")
    void testCoverImageFileSizeBiggerThan1MB() {
        //given, when, then
        assertThatThrownBy(() -> new CoverImage(FILE_SIZE_LIMIT_1MB + 1, ImageType.JPG, 300, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("fileSize must be less than 1MB");
    }

    @Test
    @DisplayName("이미지 타입이 null을 넣으면, 예외가 발생한다.")
    void testCoverImageImageTypeIsNull() {
        //given, when, then
        assertThatThrownBy(() -> new CoverImage(1000, (ImageType) null, 300, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("imageType must not be null");
    }

    @Test
    @DisplayName("너비가 300px보다 작으면, 예외가 발생한다.")
    void testCoverImageWidthBiggerThan300() {
        //given, when, then
        assertThatThrownBy(() -> new CoverImage(1000, ImageType.JPG, 300 - 1, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("width must be greater than and equal to 300 pixel");
    }

    @Test
    @DisplayName("높이가 200px보다 작으면, 예외가 발생한다.")
    void testCoverImageHeightBiggerThan200() {
        //given, when, then
        assertThatThrownBy(() -> new CoverImage(1000, ImageType.JPG, 300, 200 - 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("height must be greater than and equal to 200 pixel");
    }

    @Test
    @DisplayName("너비와 높이가 1.5를 넘어가면, 예외가 발생한다.")
    void testCoverImageWidthAndHeightBiggerThan15() {
        //given, when, then
        assertThatThrownBy(() -> new CoverImage(1000, ImageType.JPG, 300 + 1, 200 + 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ratio must be 1.5");
    }
}
