package nextstep.qna.domain;

import nextstep.qna.domain.history.DeleteHistory;
import org.junit.jupiter.api.Test;

public class DeleteHistoryTest {

    @Test
    void hasQuestion() {
        DeleteHistory deleteHistory = new DeleteHistory();
        deleteHistory.hasQuestion(QuestionTest.Q1);
        System.out.println(deleteHistory);
    }

    @Test
    void hasAnswer() {
        DeleteHistory deleteHistory = new DeleteHistory();
        deleteHistory.hasAnswer(AnswerTest.A1);
        System.out.println(deleteHistory);
    }
}
