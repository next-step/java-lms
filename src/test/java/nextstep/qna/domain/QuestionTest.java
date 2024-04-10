package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문자가 본인 질문은 삭제가 가능하다.")
    @Test
    void delete_my_question() throws CannotDeleteException {
        NsUser writer = NsUserTest.JAVAJIGI;
        Question question = Q1;

        question.delete(writer);

        Assertions.assertTrue(question.isDeleted());
    }

    @DisplayName("질문자가 아니면 질문은 삭제가 불가능하다.")
    @Test
    void delete_other_question() throws CannotDeleteException {
        NsUser writer = NsUserTest.JAVAJIGI;
        Question question = Q2;

        Assertions.assertThrows(CannotDeleteException.class, () -> {
            question.delete(writer);
        });
    }
}
