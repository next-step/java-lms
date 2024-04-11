package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageTypeTest {

    @ParameterizedTest
    @DisplayName("이미지 타입이 gif,jpg,jpeg,png,svg 중에 하나일때")
    @ValueSource(strings = {"gif", "jpg", "jpeg", "png", "svg"})
    void is_supported_type(String type) {
        ImageType imageType = ImageType.getImageType(type);
        assertThat(imageType).isEqualTo(ImageType.valueOf(type.toUpperCase()));
    }

    @ParameterizedTest
    @DisplayName("이미지 지원 타입은 대소문자 구문을 하지 않는다")
    @CsvSource(value = {"gif:GIF", "GIF:GIF"}, delimiter = ':')
    void whether_lower_or_upper_case(String type, String result) {
        ImageType imageType = ImageType.getImageType(type);
        assertThat(imageType).isEqualTo(ImageType.valueOf(result));
    }

    @Test
    @DisplayName("이미지 타입이 지원되지 않는 타입일때")
    void is_not_supported_type() {
        assertThatThrownBy(() -> {
            ImageType.getImageType("abc");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
