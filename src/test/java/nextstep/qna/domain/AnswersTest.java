package nextstep.qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnswersTest {
    @Test
    @DisplayName("질문에 해당하는 모든 답변을 조회할 수 있다.")
    void createAnswersTest() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

        assertThat(answers.getAnswers().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("질문이 추가되는 경우 답변이 추가된다.")
    void addAnswerTest() {
//        Answers answers = Answers.addAnswer(AnswerTest.A1);
    }
}
