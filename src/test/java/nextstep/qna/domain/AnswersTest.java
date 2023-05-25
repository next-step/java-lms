package nextstep.qna.domain;

import static nextstep.qna.domain.AnswerTest.A1;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class AnswersTest {
    @Test
    void shouldContainsAnswer_whenAddAnswer() {
        Answers answers = new Answers();
        answers.add(A1);

        assertThat(answers.contains(A1)).isTrue();
    }
}
