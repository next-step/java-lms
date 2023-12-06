package nextstep.qna.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class QuestionContentTest {

    @Test
    void validateQuestionContent_nsUser_테스트() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new QuestionContent(null, "title", "contents"));
    }

    @ParameterizedTest
    @CsvSource(value = {"' '", "NULL"}, nullValues = "NULL")
    void validateAnswerContent_title_contents_빈_문자열_null_테스트(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> new QuestionContent(null, input, "contents"));
        assertThatIllegalArgumentException().isThrownBy(() -> new QuestionContent(null, "title", input));
    }
}
