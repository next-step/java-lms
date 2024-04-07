package nextstep.qna.domain;

import static nextstep.qna.domain.ContentType.ANSWER;
import static nextstep.qna.domain.ContentType.QUESTION;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;

public class QuestionTest {

    public static final Question Q1 = new Question(JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("모든 답변을 포함한 질문을 삭제하고, 삭제 이력을 남긴다.")
    void deleteBy_AllQuestionAndAnswers_DeleteHistories() throws CannotDeleteException {
        final Question question = new Question(1L, JAVAJIGI, "title", "contents");
        final Answer answer = new Answer(10L, JAVAJIGI, Q1, "contents");
        question.addAnswer(answer);

        final LocalDateTime deleteDateTime = LocalDateTime.of(2024, 4, 1, 0, 0);
        final List<DeleteHistory> expectedDeleteHistories = List.of(
                new DeleteHistory(QUESTION, question.getId(), question.getWriter(), deleteDateTime),
                new DeleteHistory(ANSWER, answer.getId(), answer.getWriter(), deleteDateTime)
        );

        assertThat(question.isDeleted()).isFalse();
        assertThat(question.deleteBy(JAVAJIGI, deleteDateTime)).containsExactlyElementsOf(expectedDeleteHistories);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제 요청을 한 사용자와 질문자가 다른 경우 예외를 던진다.")
    void deleteBy_LoginUserIsNotWriter_Exception() {
        final Question question = new Question(JAVAJIGI, "title", "contents");

        assertThatThrownBy(() -> question.deleteBy(SANJIGI, LocalDateTime.now()))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문자 이외의 다른 사용자의 답변이 있는 경우 예외를 던진다.")
    void deleteBy_ExistAnswerOfDifferentWriter_Exception() {
        final Question question = new Question(JAVAJIGI, "title", "contents");
        final Answer answer = new Answer(SANJIGI, Q1, "contents");
        question.addAnswer(answer);

        assertThatThrownBy(() -> question.deleteBy(JAVAJIGI, LocalDateTime.now()))
                .isInstanceOf(CannotDeleteException.class);
    }
}
