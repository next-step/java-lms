package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    public void validateDeletable_로그인유저가_질문자면_삭제가능() throws CannotDeleteException {
        NsUser user = NsUserTest.JAVAJIGI;

        assertThat(Q1.validateDeletableBy(user)).isTrue();
    }

    @Test
    public void validateDeletable_로그인유저가_질문자와_다르면_CannotDeleteException_발생() {
        NsUser user = NsUserTest.SANJIGI;
        assertThatThrownBy(() -> Q1.validateDeletableBy(user)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_로그인유저와_질문자가_동일하면_삭제상태로_처리() throws CannotDeleteException {
        NsUser loginUser = NsUserTest.JAVAJIGI;
        Q1.delete(loginUser);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    public void delete_로그인유저와_질문자가_다르면_CannotDeleteException_발생() {
        NsUser loginUser = NsUserTest.SANJIGI;
        assertThatThrownBy(() -> Q1.delete(loginUser)).isInstanceOf(CannotDeleteException.class);
    }
}
