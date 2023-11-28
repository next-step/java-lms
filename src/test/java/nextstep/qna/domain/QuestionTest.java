package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void checkCanDelete() throws CannotDeleteException {

        Q1.addAnswer(A1);
        Q1.addAnswer(A2);

        Q2.addAnswer(A2);
        Q2.addAnswer(A3);

        Assertions.assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> Q1.checkCanDelete(NsUserTest.SANJIGI));
        Assertions.assertThat(Q2.checkCanDelete(NsUserTest.SANJIGI)).isTrue();
    }
}
