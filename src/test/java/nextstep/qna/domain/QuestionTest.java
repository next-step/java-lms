package nextstep.qna.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 질문은_삭제하면_삭제상태가_변경된다() {
        Question question = new Question(NsUser.GUEST_USER, "title1", "content");
        question.delete();
        assertThat(question.isDeleted()).isTrue();
    }

}
