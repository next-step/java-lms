package nextstep.image.domain;

import static nextstep.image.domain.ImageType.CANNOT_FIND_IMAGE_TYPE_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.image.exception.CannotFindImageTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ImageTypeTest {

    @ParameterizedTest
    @DisplayName("사용할 수 있는 이미지 타입인지 검증한다.")
    @CsvSource(value = {"gif, GIF", "jpg, JPG", "jpeg, JPEG", "png, PNG", "svg, SVG"})
    void find_image_type_by_name(String given, ImageType expected) {
        // when
        ImageType result = ImageType.findByName(given);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("원하는 이미지 타입을 찾을 수 없으면 예외를 던진다.")
    void cannot_find_image_type(){
        // when // then
        assertThatThrownBy(() -> ImageType.findByName("xrp"))
                .isExactlyInstanceOf(CannotFindImageTypeException.class)
                .hasMessage(CANNOT_FIND_IMAGE_TYPE_EXCEPTION);
    }
}
