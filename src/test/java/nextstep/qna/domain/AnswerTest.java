package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @BeforeEach
    public void setup() {
        A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    }

    @Test
    public void 답변을_작성한_사람이면_삭제할_수_있다() throws CannotDeleteException {
        Assertions.assertTrue(A1.deleted(NsUserTest.JAVAJIGI));
    }

    @Test
    public void 답변을_작성한_사람이_아니면_삭제할_수_없다() {
        Assertions.assertThrows(CannotDeleteException.class, () -> {
            A1.deleted(NsUserTest.SANJIGI);
        });
    }
}
