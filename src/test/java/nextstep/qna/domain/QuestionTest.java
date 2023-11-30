package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    private static final NsUser Q1Writer = NsUserTest.JAVAJIGI;
    private static final NsUser Q2Writer = NsUserTest.SANJIGI;
    public static final Question Q1 = new Question(Q1Writer, "title1", "contents1");
    public static final Question Q2 = new Question(Q2Writer, "title2", "contents2");

    @Test
    void 로그인사용자가_질문작성자가_아닌_경우_CannotDeleteException() {
        assertThatThrownBy(() -> {
            Q1.validateDeletable(Q2Writer);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 로그인사용자가_질문작성자인_경우_삭제가능() throws CannotDeleteException {
        Q1.validateDeletable(Q1Writer);
    }

}
