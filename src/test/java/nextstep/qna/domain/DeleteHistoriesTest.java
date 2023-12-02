package nextstep.qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nextstep.qna.domain.AnswerTest.*;
import static nextstep.qna.domain.QuestionTest.Q1;

public class DeleteHistoriesTest {

    @Test
    @DisplayName("질문 삭제 이력을 담는다")
    void 질문_삭제_이력(){
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()));

        Assertions.assertThat(deleteHistories).extracting("contentId", Long.class)
                .contains(0L);
    }

    @Test
    @DisplayName("답변 삭제 이력을 담는다")
    void 답변_삭제_이력(){
        Answers answers = new Answers(Arrays.asList(A3,A4));
        List<DeleteHistory> deleteHistories = answers.deleteAllHistories();

        Assertions.assertThat(deleteHistories).extracting("contentId", Long.class)
                .contains(1L, 2L);
    }
}
