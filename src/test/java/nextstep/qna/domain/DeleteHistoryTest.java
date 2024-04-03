package nextstep.qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class DeleteHistoryTest {
    
    @Test
    @DisplayName("삭제된 Question의 삭제 히스토리를 만든다.")
    void addQuestionDeleteHistory() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();


        Question question = QuestionTest.Q1;

        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
        for (Answer answer : question.getAnswers()) {
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }

        assertThat(deleteHistories).hasSize(1);
    }
}
