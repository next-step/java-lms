package nextstep.qna.domain;

import static nextstep.qna.domain.ContentType.ANSWER;
import static nextstep.qna.domain.QuestionTest.Q1;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;

class AnswersTest {

    @Test
    @DisplayName("모든 답변을 삭제하고, 삭제 이력을 남긴다.")
    void delete_AllAnswers_DeleteHistories() throws CannotDeleteException {
        final Answer answer1 = new Answer(1L, JAVAJIGI, Q1, "content1");
        final Answer answer2 = new Answer(2L, JAVAJIGI, Q1, "content2");
        final Answers answers = new Answers();
        answers.add(answer1);
        answers.add(answer2);

        final LocalDateTime deleteDateTime = LocalDateTime.of(2024, 4, 1, 0, 0);
        final List<DeleteHistory> expectedDeleteHistories = List.of(
                new DeleteHistory(ANSWER, answer1.id(), answer1.writer(), deleteDateTime),
                new DeleteHistory(ANSWER, answer2.id(), answer2.writer(), deleteDateTime)
        );

        assertThat(answers.delete(JAVAJIGI, deleteDateTime)).containsExactlyElementsOf(expectedDeleteHistories);
    }
}
