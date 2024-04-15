package nextstep.qna.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoriesTest {
    private static final DeleteHistories D1 = new DeleteHistories();
    private static final List<DeleteHistory> historyList = new ArrayList<>();
    @BeforeEach
    void setup() {
        historyList.add(new DeleteHistory(AnswerTest.A1));
        historyList.add(new DeleteHistory(AnswerTest.A2));
    }

    @Test
    void DeleteHistories_빈_초기화() {
        DeleteHistories deleteHistories = new DeleteHistories();
        assertThat(deleteHistories.getDeleteHistoryList()).isEmpty();
    }

    @Test
    void DeleteHistories_리스트_초기화() {
        DeleteHistories deleteHistories = new DeleteHistories(historyList);
        assertThat(deleteHistories.getDeleteHistoryList()).isEqualTo(historyList);
    }

    @Test
    void DeleteHistories_Answers_초기화() {
        Answers answers = AnswersTest.CAN_DELETED_ANSWERS;

        DeleteHistories deleteHistories = new DeleteHistories(answers);
        assertThat(deleteHistories.getDeleteHistoryList()).isNotEmpty();
    }

    @Test
    void DeleteHistories_Question_초기화() {
        Question question = QuestionTest.Q1;

        DeleteHistories deleteHistories = new DeleteHistories(question);
        assertThat(deleteHistories.getDeleteHistoryList()).hasSize(1);
    }

    @Test
    void DeleteHistories_삭제_히스토리_추가() {
        DeleteHistories deleteHistories = new DeleteHistories();
        DeleteHistory deleteHistory = new DeleteHistory(QuestionTest.Q1);

        deleteHistories.add(deleteHistory);
        assertThat(deleteHistories.getDeleteHistoryList()).containsExactly(deleteHistory);
    }

    @Test
    void DeleteHistories_리스트_삭제_히스토리_추가() {
        DeleteHistories deleteHistories = new DeleteHistories();

        deleteHistories.add(historyList);
        assertThat(deleteHistories.getDeleteHistoryList()).containsExactlyElementsOf(historyList);
    }

    @Test
    void DeleteHistories_다른_삭제_히스토리_추가() {
        DeleteHistories deleteHistories1 = new DeleteHistories();
        DeleteHistories deleteHistories2 = new DeleteHistories();
        deleteHistories2.add(new DeleteHistory(QuestionTest.Q1));
        deleteHistories1.add(deleteHistories2);

        assertThat(deleteHistories1.getDeleteHistoryList()).isEqualTo(deleteHistories2.getDeleteHistoryList());
    }
}
