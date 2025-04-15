package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    public void 질문자_불일치() {
        assertNotEquals(Q1.getWriter(), Q2.getWriter());
    }

    @Test
    public void 질문자_불일치시_예외_발생() {
        assertThatThrownBy(
            () -> Q1.deleteBy(Q2.getWriter())
        ).isInstanceOf(CannotDeleteException.class);
    }
}
