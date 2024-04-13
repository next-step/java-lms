package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeleteHistoriesTest {
    
    @Test
    @DisplayName("삭제된 Question의 삭제 히스토리를 만든다.")
    void addQuestionDeleteHistory() {
        Question question = QuestionTest.Q1;
        DeleteHistories deleteHistories = DeleteHistories.from(question);

        assertThat(deleteHistories.getDeleteHistories()).hasSize(1);
    }
}
