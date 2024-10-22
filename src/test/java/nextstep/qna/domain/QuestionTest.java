package nextstep.qna.domain;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("다른 사람의 글을 삭제하려고 하면 예외가 발생한다")
    void 작성하지_않은_글_삭제_시도_시_예외_발생() {
        Assertions.assertThatThrownBy(() ->Q1.validIfUserCanDeletePost(Q2.getWriter()))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

}
