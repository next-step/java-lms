package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GenerationTest {

    @DisplayName("기수(generation)은 0이하일 수 없다.")
    @ParameterizedTest
    @CsvSource(value = {"0", "-1"})
    void generationValidate(int generation) {
        // then
        assertThatThrownBy(() -> new Generation(generation))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
