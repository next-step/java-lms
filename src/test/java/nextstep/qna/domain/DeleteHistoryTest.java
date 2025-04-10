package nextstep.qna.domain;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.QuestionTest.Q1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class DeleteHistoryTest {

    @Test
    @DisplayName("Question과 관련된 DeleteHistory 목록을 생성한다.")
    void createDeleteHistory() {
        Question question = Q1;
        Answer answer1 = A1;
        Answer answer2 = A1;
        question.addAnswer(answer1);
        question.addAnswer(answer2);

        List<DeleteHistory> deleteHistories = DeleteHistory.create(question);

        Assertions
                .assertThat(deleteHistories)
                .hasSize(3); // 1 Question + 2 Answers
        Assertions
                .assertThat(deleteHistories)
                .extracting("contentType")
                .containsExactlyInAnyOrder(ContentType.QUESTION, ContentType.ANSWER, ContentType.ANSWER);

        Assertions
                .assertThat(deleteHistories)
                .extracting("contentId")
                .containsExactlyInAnyOrder(question.getId(), answer1.getId(), answer2.getId());
    }
}
