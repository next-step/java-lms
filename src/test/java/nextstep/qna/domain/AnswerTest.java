package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void is_owner() {
        assertThat(A1.isOwner(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    void is_not_owner() {
        assertThat(A1.isOwner(NsUserTest.SANJIGI)).isFalse();
    }

    @Test
    void delete_answers() throws CannotDeleteException {
        DeleteHistory deleted = A1.delete(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleted).isEqualTo(new DeleteHistory(A1));
    }
}
