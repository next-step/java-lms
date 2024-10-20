package nextstep.qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    private List<Answer> answerList;
    private Answers answers;

    @BeforeEach
    void setup() {
        answerList = List.of(A1, A2);
        answers = Answers.createAnswers(answerList);
    }

    @DisplayName("Answer 도메인의 일급컬렉션을 생성할 수 있다.")
    @Test
    void createAnswersTest() {
        assertThat(Answers.createAnswers(answerList).size()).isEqualTo(2);
    }

    @DisplayName("DeleteHistory 리스트를 생성할 수 있다.")
    @Test
    void generateAnswerDeleteHistoriesTest() {
        assertThat(answers.generateAnswerDeleteHistories().size()).isEqualTo(2);
    }
}
