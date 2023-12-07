package nextstep.image.domain;

import static nextstep.image.domain.ImageCapacityType.CANNOT_FIND_IMAGE_CAPACITY_TYPE_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.image.exception.CannotFindImageCapacityTypeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ImageCapacityTypeTest {

    @ParameterizedTest
    @DisplayName("이름으로 이미지 용량을 찾을 수 있다.")
    @CsvSource(value = {"mb, MB", "gb, GB"})
    void find_by_name(String given, ImageCapacityType expected) {
        // when
        ImageCapacityType result = ImageCapacityType.findByName(given);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("이름으로 이미지 용량을 찾을 수 없으면 예외를 던진다.")
    void cannot_find_image_capacity_type(){
        // given
        String tb = "tb";

        // when // then
        assertThatThrownBy(() -> ImageCapacityType.findByName(tb))
                .isExactlyInstanceOf(CannotFindImageCapacityTypeException.class)
                .hasMessage(CANNOT_FIND_IMAGE_CAPACITY_TYPE_EXCEPTION);
    }
}
