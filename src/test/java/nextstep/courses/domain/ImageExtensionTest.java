package nextstep.courses.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ImageExtensionTest {

    @ParameterizedTest(name = "확장자 {0}는 이미지 확장자 이다 {1}")
    @CsvSource(value = {"jpg,true","jpeg,true","txt,false","docs,false"})
    void isPossible(String extension, boolean expected) {
        assertThat(ImageExtension.isPossible(extension)).isEqualTo(expected);
    }
}
