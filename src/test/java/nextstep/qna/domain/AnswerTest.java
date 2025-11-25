package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 다른_사용자가_작성한_답변이_존재하면_에러발생(){
        Assertions.assertThrows(CannotDeleteException.class, () -> {
            A1.validateAnswerOwner(NsUserTest.SANJIGI);
        });
    }
}
