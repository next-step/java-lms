package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void delete_question() throws CannotDeleteException {
        Answer answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Q1.addAnswer(answer);

        assertThat(Q1.delete(NsUserTest.JAVAJIGI)).isEqualTo(new DeleteHistory(Q1));
    }

    @Test
    void delete_all() throws CannotDeleteException {
        Answer answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Q1.addAnswer(answer);

        assertThat(Q1.deleteAll(NsUserTest.JAVAJIGI)).isEqualTo(new DeletedDataHistories(
                Set.of(new DeleteHistory(Q1), new DeleteHistory(answer))
        ));
    }
}
