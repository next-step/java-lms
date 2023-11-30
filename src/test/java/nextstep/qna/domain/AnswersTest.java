package nextstep.qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

class AnswersTest {

    @Test
    @DisplayName("답변 리스트에 답변을 추가할 수 있다.")
    void answers_추가() {
        Answers answers = new Answers();

        assertThatCode(() -> answers.addAnswer(AnswerTest.A1))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("답변 리스트를 생성할 수 있다.")
    void answers_생성() {
        assertThatCode(() -> new Answers(List.of(AnswerTest.A1)))
                .doesNotThrowAnyException();
    }
}
