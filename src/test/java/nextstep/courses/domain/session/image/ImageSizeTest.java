package nextstep.courses.domain.session.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.image.ImageSize.*;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ImageSizeTest {

    @Test
    @DisplayName("이미지 사이즈 객체 정상 생성")
    void testImageSize() {
        // given
        int width = 600;
        int height = 400;

        // when
        ImageSize imageSize = ImageSize.of(width, height);

        // then
        assertEquals(imageSize.getWidth(), width);
        assertEquals(imageSize.getHeight(), height);
    }

    @Test
    @DisplayName("넓이가 300보다 작은 값이 들어와서 이미지 사이즈 객체 생성 실패")
    void testImageSize_below300Width_ShouldThrowException() {
        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            ImageSize.of(299,200);
        }).withMessageContaining(INVALID_WIDTH);
    }

    @Test
    @DisplayName("높이가 200보다 작은 값이 들어와서 이미지 사이즈 객체 생성 실패")
    void testImageSize_below300Height_ShouldThrowException() {
        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            ImageSize.of(300,199);
        }).withMessageContaining(INVALID_HEIGHT);
    }

    @Test
    @DisplayName("넓이와 높이의 비율이 3:2가 아니여서 이미지 사이즈 객체 생성 실패")
    void testImageSize_sizeRatioIsNotThreeToTwo_ShouldThrowException() {
        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            ImageSize.of(400,300);
        }).withMessageContaining(INVALID_IMAGE_RATIO);
    }
}
