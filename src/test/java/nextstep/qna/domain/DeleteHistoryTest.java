package nextstep.qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoryTest {
    
    @Test
    @DisplayName("삭제된 Question의 삭제 히스토리를 만든다.")
    void addQuestionDeleteHistory() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();


        Question question = QuestionTest.Q1;

        assertThat(question.makeDeleteHistories()).hasSize(1);
    }
}
