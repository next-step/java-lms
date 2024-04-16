package nextstep.qna.domain.question;

import static nextstep.qna.domain.history.ContentType.ANSWER;
import static nextstep.qna.domain.history.ContentType.QUESTION;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.history.DeleteHistory;

public class QuestionTest {

    public static final Question Q1 = new Question(JAVAJIGI, "title1", "contents1");

    @Test
    @DisplayName("모든 답변을 포함한 질문을 삭제하고, 삭제 이력을 남긴다.")
    void deleteBy_AllQuestionAndAnswers_DeleteHistories() {
        final Question question = new Question(1L, JAVAJIGI, "title", "contents");
        final Answer answer = new Answer(10L, JAVAJIGI, Q1, "contents");
        question.addAnswer(answer);

        final LocalDateTime deleteDateTime = LocalDateTime.of(2024, 4, 1, 0, 0);
        final List<DeleteHistory> expectedDeleteHistories = List.of(
                new DeleteHistory(QUESTION, question.id(), question.writer(), deleteDateTime),
                new DeleteHistory(ANSWER, answer.id(), answer.writer(), deleteDateTime)
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
}
