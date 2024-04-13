package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @Test
    @DisplayName("문자열을 기준으로 새로운 이름을 생성한다.")
    void Name() {
        assertThat(new Name("Java"))
                .isEqualTo(new Name("Java"));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("문자열이 null인 경우 예외를 던진다.")
    void NullName_Exception(final String nullName) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(nullName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "     ", "\n", "  \n", "  \n  "})
    @DisplayName("문자열이 빈 문자열, 공백, 개행인 경우 예외를 던진다.")
    void BlankName_Exception(final String blankName) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(blankName));
    }
}
