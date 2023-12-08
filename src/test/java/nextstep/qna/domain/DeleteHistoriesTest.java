package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeleteHistoriesTest {

    @Test
    @DisplayName("삭제된 질문을 DeletHistory에 기록한다.")
    void deleteQuestionAddDeleteHistory() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1,
            "Answers Contents1");

        Answer answer2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1,
            "Answers Contents2");

        question.addAnswer(answer1);
        question.addAnswer(answer2);

        DeleteHistories deleteHistories = new DeleteHistories(question);

        List<DeleteHistory> histories = deleteHistories.getDeleteHistories();
        assertThat(histories).hasSize(3);
    }

}
