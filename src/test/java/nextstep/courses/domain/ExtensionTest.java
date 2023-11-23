package nextstep.courses.domain;

import nextstep.courses.NotExtensionTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExtensionTest {

    @Test
    @DisplayName("gif, jpg, jpeg, png, svg가 아닐 경우 예외 처리 된다")
    void newExtension() {
        assertThrows(NotExtensionTypeException.class, () -> Extension.of("xls"), "올바르지 않은 파일 타입입니다.");
    }

    @ParameterizedTest(name = "입력값이 {0} 이면 enum {1}을 생성한다")
    @CsvSource({"gif, GIF", "jpg, JPG", "jpeg, JPEG", "png, PNG", "svg, SVG"})
    void newExtension2(String input,
                       Extension expected) {
        Extension actual = Extension.of(input);

        assertThat(actual).isEqualTo(expected);
    }
}
