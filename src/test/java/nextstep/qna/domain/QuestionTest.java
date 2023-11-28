package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 질문은_삭제하면_삭제상태가_변경된다() throws CannotDeleteException {
        Question question = new Question(NsUser.GUEST_USER, "title1", "content");
        question.delete(NsUser.GUEST_USER);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    void 질문은_올린사람만_삭제_가능하다() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 질문을_삭제하면_삭제히스토리가_나온다() throws CannotDeleteException {
        LocalDateTime time = LocalDateTime.now();
        DeleteHistory history = new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), time);
        assertThat(Q1.delete(NsUserTest.JAVAJIGI, time)).isEqualTo(history);
    }

}
