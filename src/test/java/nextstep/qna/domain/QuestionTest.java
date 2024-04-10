package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문 작성자가 아닐 때 삭제 할 수 없음")
    void question_not_ower() {
        assertThatThrownBy(() -> Q1.checkOwner(Q2, NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

}
