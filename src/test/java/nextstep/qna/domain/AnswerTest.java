package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void delete() throws Exception {
        assertThat(A1.delete(NsUserTest.JAVAJIGI)).isEqualTo(DeleteHistoryTest.A1DeleteHistory);
    }

    @Test
    public void deleteByWrongUser() {
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
