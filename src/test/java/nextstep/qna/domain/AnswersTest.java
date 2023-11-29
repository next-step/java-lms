package nextstep.qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnswersTest {
    @Test
    @DisplayName("여러 Answer를 일급콜렉션에 저장한다.")
    void createAnswers() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));
        assertThat(answers).isEqualTo(2);
    }
}
