package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문한 유저가 아닌 유저가 질문을 삭제하려고 시도할 경우, CannotDeleteException 예외 발생")
    void user_who_is_not_the_owner_of_question_try_to_delete_question_then_throw_throw_CannotDeleteException() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문자와 답변자가 다른 다를 때 질문을 삭제하려고 시도할 경우, CannotDeleteException 예외 발생")
    void try_to_delete_question_when_questioner_and_answerer_are_different_then_throw_CannotDeleteException() {
        // given
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(AnswerTest.A2);

        // when, then
        assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("질문 데이터를 삭제하면 질문의 상태를 삭제로 변경")
    void delete_question_then_set_deleted_to_true() throws CannotDeleteException {
        // given
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        // when
        question.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(question.isDeleted()).isTrue();
    }
}
