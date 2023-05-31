package nextstep.courses.domain.session;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ImageTypeTest {

    @ParameterizedTest(name = "지원하는 이미지 형식 검사")
    @ValueSource(strings = {"https://www.test.com/img.png", "https://www.test.com/img.gif", "https://www.test.com/img.jpg", "https://www.test.com/img.jpeg"})
    void test1(String urlString) {
        assertTrue(
                Arrays.stream(ImageType.values())
                        .filter(value -> value.equals(ImageType.of(urlString)))
                        .findFirst()
                        .isPresent()
        );
    }

    @ParameterizedTest(name = "지원하지 않는 이미지 형식 검사")
    @ValueSource(strings = {"https://www.test.com/img.abc", "https://www.test.com/img"})
    void test2(String urlString) {
        assertThatThrownBy(() -> {
            ImageType.of(urlString);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
