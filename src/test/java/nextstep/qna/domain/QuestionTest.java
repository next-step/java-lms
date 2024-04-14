package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void delete_question() throws CannotDeleteException {
        Answer answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Q1.addAnswer(answer);
        Q1.deleteAll(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void delete_all() throws CannotDeleteException {
        Q1.deleteAll(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }
}
