package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {

    @DisplayName("질문자와 로그인 유저가 같음")
    @Test
    public void writerAndLoginEquals() throws CannotDeleteException {
        Question Q1 = new Question("title1", new Contents(NsUserTest.JAVAJIGI, "contents1"));
        Question Q2 = new Question("title2", new Contents(NsUserTest.SANJIGI, "contents2"));

        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();

        Q2.delete(NsUserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isTrue();
    }

    @DisplayName("질문자와 로그인 유저가 다름")
    @Test
    public void writerAndLoginNotEquals() {
        Question Q1 = new Question("title1", new Contents(NsUserTest.JAVAJIGI, "contents1"));
        Question Q2 = new Question("title2", new Contents(NsUserTest.SANJIGI, "contents2"));

        assertThatThrownBy(() -> {
            Q1.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);

        assertThatThrownBy(() -> {
            Q2.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
