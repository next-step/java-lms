package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import fixture.qna.domain.AnswersFixture;

public class DeleteHistoriesTest {

    @ParameterizedTest
    @EnumSource(AnswersFixture.class)
    @DisplayName("삭제된 Answers의 삭제 히스토리를 만든다.")
    void addAnswersDeletedHistories(AnswersFixture answersFixture) {
        DeleteHistories deleteHistories = new DeleteHistories();
        Answers answers = answersFixture.getAnswers();

        deleteHistories.addAnswersDeletedHistories(answers);

        List<DeleteHistory> deleteHistories1 = deleteHistories.getDeleteHistories();

        assertThat(deleteHistories.getDeleteHistories()).hasSize(2);
    }

    @Test
    @DisplayName("삭제된 Question의 삭제 히스토리를 만든다.")
    void addQuestionDeleteHistory() {
        DeleteHistories deleteHistories = new DeleteHistories();
        Question question = QuestionTest.Q1;

        deleteHistories.addQuestionDeleteHistory(question);

        assertThat(deleteHistories.getDeleteHistories()).hasSize(1);
    }
}
