package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문자와 로그인 유저가 같음")
    @Test
    public void writerAndLoginEquals() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("질문자와 로그인 유저가 다름")
    @Test
    public void writerAndLoginNotEquals() {
        assertThatThrownBy(() -> {
            Q1.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
