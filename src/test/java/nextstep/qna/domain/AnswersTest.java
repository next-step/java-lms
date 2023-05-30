package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnswersTest {

    private Answers answers;

    @BeforeEach
    void setup() {
        answers = new Answers();
    }

    @Test
    void 질문의_답변_전체_삭제_테스트() {
        answerMockData();

        final var deleteHistories = answers.deleteAnswers(A1.getWriter());

        assertThat(deleteHistories.getDeleteHistories()).hasSize(2);
    }

    @Test
    void 답변_삭제시_질문자와_답변자가_다른경우_예외를_던진다() {
        answerThrowMockData();

        assertThrows(
                CannotDeleteException.class,
                () -> answers.deleteAnswers(A1.getWriter())
        );
    }

    private void answerMockData() {
        answers.add(A1);
        answers.add(A1);
    }

    private void answerThrowMockData() {
        answers.add(A1);
        answers.add(A2);
        answers.add(A1);
    }

}
