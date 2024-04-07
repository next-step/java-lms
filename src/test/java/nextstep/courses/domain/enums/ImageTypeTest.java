package nextstep.courses.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class ImageTypeTest {

    @ParameterizedTest
    @MethodSource("findByTypeAndImageType")
    @DisplayName("문자열이 매개변수로 넘어갔을 때 문자열과 이름이 일치하는 ImageType 반환 (대소문자 구분X) ")
    void findByType(ImageType findType, ImageType expected) {
        assertThat(findType).isEqualTo(expected);
    }

    @Test
    @DisplayName("문자열이 매개변수로 넘어갔을 때, 문자열과 이름이 일치하는 ImageType이 없으면 Optional.empty() 반환")
    void findByType_isEmpty() {
        assertThat(ImageType.findByType("abc")).isEmpty();
    }

    static Stream<Object> findByTypeAndImageType() {
        return Stream.of(
                Arguments.arguments(ImageType.findByType("gif").orElseThrow(), ImageType.GIF),
                Arguments.arguments(ImageType.findByType("jPg").orElseThrow(), ImageType.JPG),
                Arguments.arguments(ImageType.findByType("JPEg").orElseThrow(), ImageType.JPEG),
                Arguments.arguments(ImageType.findByType("PNG").orElseThrow(), ImageType.PNG),
                Arguments.arguments(ImageType.findByType("svG").orElseThrow(), ImageType.SVG)
        );
    }
}