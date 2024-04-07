package nextstep.qna.domain;

import static nextstep.qna.domain.ContentType.ANSWER;
import static nextstep.qna.domain.ContentType.QUESTION;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    public static final Question Q1 = new Question(JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("모든 답변을 포함한 질문을 삭제하고, 삭제 이력을 남긴다.")
    void deleteBy_AllQuestionAndAnswers_DeleteHistories() {
        final Question question = new Question(1L, JAVAJIGI, "title", "contents");
        final Answer answer = new Answer(10L, JAVAJIGI, Q1, "contents");
        question.addAnswer(answer);

        assertThat(question.isDeleted()).isFalse();

        final LocalDateTime deleteDateTime = LocalDateTime.of(2024, 4, 1, 0, 0);

        assertThat(question.deleteBy(JAVAJIGI, deleteDateTime)).containsExactly(
                new DeleteHistory(QUESTION, question.getId(), question.getWriter(), deleteDateTime),
                new DeleteHistory(ANSWER, answer.getId(), answer.getWriter(), deleteDateTime));
        assertThat(question.isDeleted()).isTrue();
    }
}
