package nextstep.qna.domain;


import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AnswersTest {
    @DisplayName("답변 추가 테스트")
    @Test
    void 답변_추가() {
        Answers answers = new Answers();
        answers.addAnswer(A1);
        answers.addAnswer(A2);

        assertThat(answers).isEqualTo(new Answers(List.of(A1, A2)));
    }

    @DisplayName("글작성자 이외 다른 작성자 답변 있을 시 에러")
    @Test
    void 다른작성자_에러() {
        Answers answers = new Answers(List.of(A1, A2));
        assertThatThrownBy(() -> {
            answers.validateDeleteQuestionAnswers(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("답변 전체 삭제")
    @Test
    void 답변_전체_삭제() {
        Answers answers = new Answers(List.of(A1, A1));
        DeleteHistories deleteHistories = new DeleteHistories();

        answers.deleteQuestionAllAnswers(deleteHistories);
        assertAll(
                () -> assertThat(answers.getAnswers().get(0).isDeleted()).isTrue(),
                () -> assertThat(answers.getAnswers().get(1).isDeleted()).isTrue()
        );
    }
}
