package nextstep.courses.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImageTypeTest {

    @DisplayName("이미지 타입이 올바른지 검증하는 메서드가 정상적으로 수행한다.")
    @ParameterizedTest
    @ValueSource(strings = {"jpeg", "png", "gif"})
    void isValidType_should_return_true_for_valid_types(String type) {
        assertTrue(ImageType.isValidType(type));
    }

    @DisplayName("이미지 타입을 지원하지 않을때 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"bmp", "abc", "def"})
    void isValidType_should_return_false_for_invalid_types(String type) {
        assertFalse(ImageType.isValidType(type));
    }

}