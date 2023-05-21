package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void deleteAnswer() {
        assertThat(A1.delete()).isInstanceOf(DeleteHistory.class);
        assertThat(A1.isDeleted()).isTrue();
    }
}
