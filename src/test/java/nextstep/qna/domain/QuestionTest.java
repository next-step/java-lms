package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 질문작성자와_로그인유저가_같은경우() {
        assertThatNoException()
                .isThrownBy(() -> Q1.canDelete(NsUserTest.JAVAJIGI));
    }

    @Test
    void 질문작성자와_로그인유저가_다른경우_예외를던진다() {
        assertThatThrownBy(() -> Q1.canDelete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                        .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

}
