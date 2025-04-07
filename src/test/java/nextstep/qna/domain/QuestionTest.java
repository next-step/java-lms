package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    public void 작성자가_답변이없는질문을_삭제한다() throws Exception {
        Q1.deleteBy(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    public void 작성자가_아니면_답변이없는질문을_삭제시_예외가_발생한다() throws Exception {
        assertThatThrownBy(() -> Q2.deleteBy(NsUserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

}
