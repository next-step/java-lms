package nextstep.courses.domian;

import nextstep.courses.domain.ImageExtension;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ImageExtensionTest {

    @ParameterizedTest(name = "확장자 {0}는 이미지 확장자 이다 {1}")
    @CsvSource(value = {"jpg,true","jpeg,true","txt,false","docs,false"})
    void isPossible(String extension, boolean expected) {
        Assertions.assertThat(ImageExtension.isPossible(extension)).isEqualTo(expected);
    }
}
