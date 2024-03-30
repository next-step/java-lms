package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswersTest {

    @Test
    @DisplayName("모든 답변의 작성자가 본인이라면 성공적으로 답변들을 삭제한다")
    void delete() throws CannotDeleteException {
        // given
        List<Answer> answersList = List.of(AnswerTest.A1);
        Answers answers = new Answers(answersList);

        // when
        List<DeleteHistory> deleteHistories = answers.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(deleteHistories).hasSize(1);
    }

    @Test
    @DisplayName("작성자 본인이 아닌 답변이 하나라도 있는데 삭제 시 예외가 발생한다")
    void delete_fail_for_not_owner() {
        // given
        List<Answer> answersList = List.of(AnswerTest.A1, AnswerTest.A2);
        Answers answers = new Answers(answersList);

        // when, then
        assertThatThrownBy(() -> answers.delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }
}
