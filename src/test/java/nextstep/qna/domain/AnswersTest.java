package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.answer.Answers;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AnswersTest {
    public static final Answers ANSWERS1 = new Answers(List.of(AnswerTest.A1, AnswerTest.A1));

    @Test
    void canDeleteAllBy_정상_케이스() {
        Assertions.assertDoesNotThrow(() -> ANSWERS1.deletedBy(NsUserTest.JAVAJIGI));
    }

    @Test
    void canDeleteAllBy_예외_케이스() {
        Assertions.assertThrows(CannotDeleteException.class, () -> ANSWERS1.deletedBy(NsUserTest.SANJIGI));
    }
}
