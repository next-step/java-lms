package nextstep.qna.domain;

import static nextstep.qna.domain.history.ContentType.ANSWER;
import static nextstep.qna.domain.QuestionTest.Q1;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.history.DeleteHistory;
import nextstep.qna.domain.question.Answer;

class AnswerTest {

    @Test
    @DisplayName("하나의 답변을 삭제하고, 삭제 이력을 남긴다.")
    void delete_Answer_DeleteHistory() throws CannotDeleteException {
        final Answer answer = new Answer(JAVAJIGI, Q1, "contents");
        final LocalDateTime deleteDateTime = LocalDateTime.of(2024, 4, 1, 0, 0);

        assertThat(answer.delete(JAVAJIGI, deleteDateTime))
                .isEqualTo(new DeleteHistory(ANSWER, answer.id(), answer.writer(), deleteDateTime));
    }

    @Test
    @DisplayName("질문자 이외의 다른 사용자의 답변이 있는 경우 예외를 던진다.")
    void delete_AnswerWriterIsNotQuestionWriter_Exception() {
        final Answer answer = new Answer(JAVAJIGI, Q1, "contents");

        assertThatThrownBy(() -> answer.delete(SANJIGI, LocalDateTime.now()))
                .isInstanceOf(CannotDeleteException.class);
    }
}
