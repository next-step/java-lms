package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static nextstep.qna.domain.ContentType.*;
import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문한 유저가 아닌 유저가 질문을 삭제하려고 시도할 경우, CannotDeleteException 예외 발생")
    void user_who_is_not_the_owner_of_question_try_to_delete_question_then_throw_throw_CannotDeleteException() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI, 0L))
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
        assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI, 0L))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("질문 데이터를 삭제하면 질문의 상태를 삭제로 변경")
    void delete_question_then_set_deleted_status_to_true() throws CannotDeleteException {
        // given
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        long questionId = 0L;

        // when
        question.delete(NsUserTest.JAVAJIGI, questionId);

        // then
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문 데이터를 삭제하면, 답변들의 상태를 삭제로 변경")
    void delete_question_then_set_answers_deleted_status_to_true() throws CannotDeleteException {
        // given
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(AnswerTest.A1);
        long questionId = 0L;

        // when
        question.delete(NsUserTest.JAVAJIGI, questionId);

        // then
        Optional<Answer> notDeletedAnswer = question.getAnswers().stream()
                .filter(answer -> !answer.isDeleted())
                .findAny();
        assertThat(notDeletedAnswer).isEmpty();
    }

    @Test
    @DisplayName("질문 삭제 시, 질문과 답변의 삭제 이력 리스트 반환")
    void delete_question_then_return_delete_history_list_of_question_and_answers() throws CannotDeleteException {
        // given
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = AnswerTest.A1;
        question.addAnswer(answer);
        long questionId = 0L;

        // when
        List<DeleteHistory> deleteHistories = question.delete(NsUserTest.JAVAJIGI, questionId);

        // then
        assertThat(deleteHistories).containsExactly(
                new DeleteHistory(QUESTION, questionId, question.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now())
        );
    }
}
