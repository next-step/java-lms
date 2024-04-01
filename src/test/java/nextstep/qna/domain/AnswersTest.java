package nextstep.qna.domain;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;

class AnswersTest {

    @Test
    void 답변을_삭제할_떼_다른_사람이_쓴_답변이_있으면_예외가_발생한다() {
        final Answers answers = new Answers(List.of(A1, A2));

        assertThatThrownBy(() -> answers.validateAnswersOwnership(JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
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
