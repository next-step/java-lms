package nextstep.courses.domain.session.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static nextstep.courses.domain.session.image.ImageTypeEnum.INVALID_IMAGE_TYPE;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ImageTypeEnumTest {

    @ParameterizedTest(name = "이미지 타입 객체 성공")
    @ValueSource(strings = {"gif", "jpg", "jpeg", "png", "svg"})
    void testImageTypeEnum(String type) {
        // given
        String imageFormat = type;

        // when
        ImageTypeEnum imageType = ImageTypeEnum.of(imageFormat);

        // then
        assertEquals(imageType.getImageType(), imageFormat);
    }

    @Test
    @DisplayName("JPG 이미지 타입 생성 (대문자 -> 소문자로 인식)")
    void testImageType_upperCaseToLowerCase() {
        // given
        String imageFormat = "JPG";

        // when
        ImageTypeEnum imageType = ImageTypeEnum.of(imageFormat);

        // then
        assertEquals(imageType.getImageType(), "jpg");
    }

    @Test
    @DisplayName("유효하지 않은 이미지 포맷으로, 이미지 타입 생성 실패")
    void testImageType_invalidImageFormat_ShouldThrowException() {
        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            ImageTypeEnum.of("exe");
        }).withMessageContaining(INVALID_IMAGE_TYPE);
    }

}
