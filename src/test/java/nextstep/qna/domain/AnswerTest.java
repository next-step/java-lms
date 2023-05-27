package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answer answer;

    @BeforeEach
    void beforeEach() {
        answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents");
    }

    @Test
    void deleteAnswer() {
        assertThat(answer.delete()).isEqualTo(
            new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        assertThat(answer.isDeleted()).isTrue();
    }
}
