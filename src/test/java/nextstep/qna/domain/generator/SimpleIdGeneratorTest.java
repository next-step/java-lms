package nextstep.qna.domain.generator;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class SimpleIdGeneratorTest {


    @BeforeEach
    public void setUp() {
        SimpleIdGenerator.initialize();
    }

    @Test
    @DisplayName("처음 생성되는 Question ID 확인")
    void question_id_확인() {

        long id = SimpleIdGenerator.getAndIncrement(Question.class);
        assertThat(id).isEqualTo(1);

    }

    @Test
    @DisplayName("처음 생성되는 Question,Answer,DeleteHistories ID 확인")
    void question_answer_deleteHistories_id_확인() {

        long questionId = SimpleIdGenerator.getAndIncrement(Question.class);
        long answerId = SimpleIdGenerator.getAndIncrement(Answer.class);
        long deleteHistoryId = SimpleIdGenerator.getAndIncrement(DeleteHistory.class);

        assertAll(
                () -> assertThat(questionId).isEqualTo(1),
                () -> assertThat(answerId).isEqualTo(1),
                () -> assertThat(deleteHistoryId).isEqualTo(1)
        );
    }

    @Test
    @DisplayName("2번째 생성되는 Question,Answer,DeleteHistories ID 확인")
    void 두번째_question_answer_deleteHistories_id_확인() {
        SimpleIdGenerator.getAndIncrement(Question.class);
        SimpleIdGenerator.getAndIncrement(Answer.class);
        SimpleIdGenerator.getAndIncrement(DeleteHistory.class);

        long questionId = SimpleIdGenerator.getAndIncrement(Question.class);
        long answerId = SimpleIdGenerator.getAndIncrement(Answer.class);
        long deleteHistoryId = SimpleIdGenerator.getAndIncrement(DeleteHistory.class);

        assertAll(
                () -> assertThat(questionId).isEqualTo(2L),
                () -> assertThat(answerId).isEqualTo(2L),
                () -> assertThat(deleteHistoryId).isEqualTo(2L)
        );
    }


}