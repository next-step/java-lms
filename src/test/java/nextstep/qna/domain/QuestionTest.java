package nextstep.qna.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    public void 생성() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title", "contents");

        assertThat(question).isInstanceOf(Question.class);
    }

    @Test
    public void 삭제_성공_답변이_없는_경우() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    public void 삭제_실패_질문자와_답변자가_다른_경우() {
        Answer answer = new Answer(NsUserTest.SANJIGI, Q1, "contents");

        Q1.addAnswer2(answer);

        assertThatThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void 삭제_실패_로그인_유저와_작성자가_다른_경우() {
        NsUser writer = NsUserTest.JAVAJIGI;
        NsUser loginUser = NsUserTest.SANJIGI;

        Question question = new Question(writer, "title", "contents");

        assertThatThrownBy(() -> question.delete(loginUser))
            .isInstanceOf(CannotDeleteException.class);
    }
}
