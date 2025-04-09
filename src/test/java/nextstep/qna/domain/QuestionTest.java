package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 질문_작성자가_자신일_경우_예외가_발생하지_않는다() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);
    }

    @Test
    void 질문_작성자가_타인일_경우_예외가_발생한다() {
        Assertions.assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }
}
