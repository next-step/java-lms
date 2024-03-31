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
    @DisplayName("질문자가 로그인한 본인이 아닐 경우에 삭제가 불가능")
    public void is_not_login_user() {
        assertThatThrownBy(() -> {
            Q1.checkIfOwner(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
