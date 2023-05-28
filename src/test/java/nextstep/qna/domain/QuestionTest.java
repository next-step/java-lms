package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 다른사람_쓴_질문_삭제_불가() {
        assertThatThrownBy(() -> {
            Q1.delete(Q2.getWriter());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 답변삭제와_삭제히스토리생성() {
        List<DeleteHistory> deleteHistory = Q1.delete(Q1.getWriter());
        assertTrue(Q1.isDeleted());
        assertThat(deleteHistory).isNotNull();
    }

    @Test
    void 작성자확인() {
        assertTrue(Q2.isOwner(Q2.getWriter()));
    }
}
