package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {
    @AfterEach
    void tearDown() {
        AnswerTest.A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    @Test
    @DisplayName("답변 추가")
    void add() {
        // given
        Answers answers = new Answers();
        Answer answer = new Answer();

        // when
        answers.add(answer);

        // then
        assertThat(answers).isEqualTo(new Answers(answer));
    }

    @Test
    @DisplayName("답변 삭제")
    void delete() throws CannotDeleteException {
        // given
        Answers answers = new Answers();
        Answer answer = AnswerTest.A1;
        answers.add(answer);

        // when
        DeleteHistories deleteHistories = answers.deleteAll(answer.getWriter());

        // then
        assertThat(deleteHistories).isEqualTo(new DeleteHistories(
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now())
        ));
    }

    @Test
    @DisplayName("답변 삭제 - 답변 중 이미 삭제된 답변이 있는 경우")
    void delete1() throws CannotDeleteException {
        // given
        Answer answer = AnswerTest.A1;
        Answer answer2 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
        Answers answers = new Answers(answer, answer2);

        // when
        answer.delete(answer.getWriter());

        // then
        assertThat(answers.deleteAll(answer.getWriter())).isEqualTo(new DeleteHistories(
                new DeleteHistory(ContentType.ANSWER, answer2.getId(), answer2.getWriter(), LocalDateTime.now())
        ));
    }
}