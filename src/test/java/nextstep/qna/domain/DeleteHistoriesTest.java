package nextstep.qna.domain;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static nextstep.qna.domain.QuestionTest.Q1;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DeleteHistoriesTest {

    @Test
    void 추가() {
        // given
        Q1.addAnswer(A1);
        Q1.addAnswer(A2);

        final DeleteHistories deleteHistories = new DeleteHistories();

        // when
        deleteHistories.add(Q1);

        // then
        assertThat(deleteHistories.getDeleteHistories()).hasSize(3);
    }
}
