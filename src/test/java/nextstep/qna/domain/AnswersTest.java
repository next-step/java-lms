package nextstep.qna.domain;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;

class AnswersTest {

    @Test
    void 답변을_삭제한다() throws CannotDeleteException {
        // given
        final Answers answers = new Answers(List.of(A1));

        // when
        final List<DeleteHistory> deleteHistory = answers.delete(JAVAJIGI);

        // then
        assertThat(deleteHistory).hasSize(1);
    }

    @Test
    void 답변을_추가한다() {
        // given
        final Answers answers = new Answers();

        // when
        answers.add(A1);

        // then
        assertThat(answers.getAnswers()).containsExactly(A1);
    }
}
