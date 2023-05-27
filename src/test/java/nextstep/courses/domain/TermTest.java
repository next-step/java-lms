package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("기수 객체 테스트")
class TermTest {

    @ParameterizedTest(name = "기수 객체를 생성할때 양수가 아니면 예외가 발생한다")
    @ValueSource(ints = {0, -9})
    void termException(int inputs) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Term(inputs))
                .withMessage("the term is must be greater than zero");
    }

    @ParameterizedTest(name = "기수 객체는 양수로 생성하면 정상적으로 생성된다")
    @CsvSource(value = {"1:1기", "100:100기"}, delimiter = ':')
    void term(int inputTerm, String expectedTermValue) {
        Term term = new Term(inputTerm);
        assertThat(term.ternValue()).isEqualTo(expectedTermValue);
    }
}
