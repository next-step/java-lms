package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
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
    @DisplayName("질문에 답변이 추가되는 경우 답변이 추가된다.")
    void addAnswerTest() {
        Answers initAnswer = Answers.initialize();

        assertThat(initAnswer.addAnswer(AnswerTest.A1).size()).isEqualTo(1);
    }

    @Test
    @DisplayName("질문에 해당하는 답변을 가져온다.")
    void getAnswerCountTest() {
        Answers answers = Answers.initialize();

    }
}
