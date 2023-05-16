package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void deleteAnswer() {
        Assertions.assertThat(A1.deleteAnswers()).isEqualTo(new DeleteHistory(ContentType.ANSWER, null, A1.getWriter(), LocalDateTime.now()));
        Assertions.assertThat(A2.deleteAnswers()).isEqualTo(new DeleteHistory(ContentType.ANSWER, null, A2.getWriter(), LocalDateTime.now()));
    }
}
