package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class AnswersTest {
    @Test
    void add_answer() {
        // given
        Answers answers = new Answers();

        // when
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        // then
        assertThat(answers).isEqualTo(new Answers(AnswerTest.A1, AnswerTest.A2));
    }

    @Test
    @DisplayName("답변 삭제 시, deleted 상태를 true로 변경")
    void delete_then_set_deleted_status_to_true() throws CannotDeleteException {
        // given
        Answers answers = new Answers(AnswerTest.A1);

        // when
        answers.delete(NsUserTest.JAVAJIGI);

        // then
        Optional<Answer> notDeletedAnswer = answers.answers().stream()
                .filter(answer -> !answer.isDeleted())
                .findAny();
        assertThat(notDeletedAnswer).isEmpty();
    }

    @Test
    @DisplayName("답변한 유저가 아닌 유저가 답변을 삭제하려고 시도할 경우, CannotDeleteException 예외 발생")
    void user_who_is_not_the_owner_of_answer_try_to_delete_question_then_throw_throw_CannotDeleteException() {
        // given
        Answers answers = new Answers(AnswerTest.A1, AnswerTest.A2);

        // when, then
        assertThatThrownBy(() -> answers.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("답변 삭제 시, DeleteHistory 리스트 반환")
    void delete_then_return_DeleteHistory_list() {
        fail("not yet implemented");
    }
}
