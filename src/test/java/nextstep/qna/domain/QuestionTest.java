package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 질문을 삭제한다")
    void delete_same_user() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();

        Q2.delete(NsUserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 다른 경우 질문 삭제는 불가능하다")
    void delete_different_user() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);

        assertThatThrownBy(() -> Q2.delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }
}
