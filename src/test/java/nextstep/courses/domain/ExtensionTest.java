package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ExtensionTest {

    @ParameterizedTest
    @CsvSource(value = {"'gif':GIF", "'jpg':JPG", "'jpeg':JPEG", "'png':PNG", "'svg':SVG"}, delimiter = ':')
    @DisplayName("확장자 문자열에 맞는 Extension 객체를 반환한다.")
    void extension_test(String name, Extension extension) {
        assertThat(Extension.from(name)).isEqualTo(extension);
    }
}
