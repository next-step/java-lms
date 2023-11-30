package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class AnswersTest {
    private final Answer A0 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    private final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    private final Answer A2 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 답변삭제권한_exception() {
        Answers answers = new Answers(List.of(A0, A1));
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> answers.deleteAnswers(NsUserTest.JAVAJIGI))
                .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void deleteAnswers() throws CannotDeleteException {
        Answers answers = new Answers(List.of(A1, A2));
        assertThat(answers.deleteAnswers(NsUserTest.JAVAJIGI)).containsExactly(
                new DeleteHistory(ContentType.ANSWER, A1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, A2.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }
}