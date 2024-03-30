package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(SANJIGI, "title2", "contents2");

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question(JAVAJIGI, "title3", "contents3");
    }

    @Test
    void 질문작성자가_일치하고_답변이없는경우_삭제가능하다() {
        assertThatNoException()
                .isThrownBy(() -> question.canDelete(JAVAJIGI));
    }

    @Test
    void 질문작성자와_로그인유저가_다른경우_예외를던진다() {
        assertThatThrownBy(() -> question.canDelete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 질문과_답변자_작성자가_모두_같은_경우_삭제가능하다() {
        question.addAnswer(AnswerTest.A1);
        assertThatNoException()
                .isThrownBy(() -> question.canDelete(JAVAJIGI));
    }

    @Test
    void 질문과_답변자_작성자가_일치하지않는경우_예외를_던진다() {
        question.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> question.canDelete(JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
