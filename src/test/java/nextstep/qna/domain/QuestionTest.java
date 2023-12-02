package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("로그인유저와 질문작성자가 다른경우 오류")
    @Test
    void 질문작성자_테스트() {
        Assertions.assertThatThrownBy(() -> {
           Q1.isValidLoginUser(NsUser.GUEST_USER);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
