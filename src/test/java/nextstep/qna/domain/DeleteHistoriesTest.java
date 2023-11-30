package nextstep.qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nextstep.qna.domain.AnswerTest.*;
import static nextstep.qna.domain.QuestionTest.Q1;

public class DeleteHistoriesTest {

    @Test
    @DisplayName("질문 삭제 이력을 담는다")
    void 질문_삭제_이력(){
        DeleteHistories deleteHistories = new DeleteHistories(new ArrayList<>());
//        deleteHistories.addQuestionDeleteHistory(Q1);

        Assertions.assertThat(deleteHistories.getDeleteHistories()).extracting("contentId", Long.class)
                .contains(0L);
    }

    @Test
    @DisplayName("답변 삭제 이력을 담는다")
    void 답변_삭제_이력(){
        Answers answers = new Answers(Arrays.asList(A3,A4));
        DeleteHistories deleteHistories = new DeleteHistories(answers.deleteAllHistories());

        Assertions.assertThat(deleteHistories.getDeleteHistories()).extracting("contentId", Long.class)
                .contains(1L, 2L);
    }
}
