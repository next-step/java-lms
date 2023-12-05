package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {
    private final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    private final Answer A2 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void deleteAnswers() throws CannotDeleteException {
        Answers answers = new Answers(List.of(A1, A2));
        assertThat(answers.deleteAnswers(NsUserTest.JAVAJIGI)).containsExactly(
                DeleteHistory.ofAnswer(A1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()),
                DeleteHistory.ofAnswer(A2.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }
}