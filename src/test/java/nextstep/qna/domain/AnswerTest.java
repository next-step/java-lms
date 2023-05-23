package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void delete() {
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    public void isOwner_True() {
        assertThat(A1.isOwner(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    public void isOwner_False() {
        assertThat(A1.isOwner(NsUserTest.SANJIGI)).isFalse();
    }
}
