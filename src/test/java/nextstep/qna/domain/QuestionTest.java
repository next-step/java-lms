package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @BeforeEach
    public void setup() {
        Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    }

    @Test
    public void 질문을_성공적으로_삭제하면_true를_반환한다() throws CannotDeleteException {
        Assertions.assertTrue(Q1.deleted(NsUserTest.JAVAJIGI));
    }

    @Test
    public void 질문자가_아닌_사람의_답변이_있으면_Exception이_발생한다() {
        Q1.addAnswer(new Answer(NsUserTest.SANJIGI, Q1, "test"));
        Assertions.assertThrows(CannotDeleteException.class, () -> {
            Q1.deleted(NsUserTest.JAVAJIGI);
        });
    }

    @Test
    public void 질문자가_아닌_사람이_삭제를_시도하면_Exception이_발생한다() {
        Assertions.assertThrows(CannotDeleteException.class, () -> {
            Q1.deleted(NsUserTest.SANJIGI);
        });
    }
}
