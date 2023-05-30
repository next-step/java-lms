package nextstep.qna.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    private Question question;

    private DeleteHistories deleteHistories;

    @BeforeEach
    void setUp() {
        deleteHistories = new DeleteHistories();

        question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents", deleteHistories);
    }

    @Test
    public void 생성() {
        assertThat(question).isInstanceOf(Question.class);
    }

    @Test
    public void 삭제_성공_답변이_없는_경우() throws CannotDeleteException {
        question.delete(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistories.size()).isEqualTo(1);
    }

    @Test
    public void 삭제_실패_질문자와_답변자가_다른_경우() {
        Answer answer = new Answer(NsUserTest.SANJIGI, Q1, "contents");

        question.addAnswer2(answer);

        assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void 삭제_실패_로그인_유저와_작성자가_다른_경우() {
        NsUser loginUser = NsUserTest.SANJIGI;

        assertThatThrownBy(() -> question.delete(loginUser))
            .isInstanceOf(CannotDeleteException.class);
    }
}
