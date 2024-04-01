package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {

    public static Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @BeforeEach
    void beforeEach() {
        Q1 = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
        Q2 = new Question(2L, NsUserTest.SANJIGI, "title2", "contents2");
    }

    @Test
    void 질문자와_로그인_사용자가_다른_경우_삭제_불가능() {
        assertThatThrownBy(() -> Q1.deleteAndGetHistories(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 삭제시_삭제상태_변화() {
        Q2.deleteAndGetHistories(NsUserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isTrue();
    }

    @Test
    void 답변이_없는_경우_삭제_가능() {
        Q1.deleteAndGetHistories(NsUserTest.JAVAJIGI);
    }

    @Test
    void 질문자와_답글의_모든_답변자가_같은_경우_삭제_가능() {
        Q1.addAnswer(new Answer(NsUserTest.JAVAJIGI, Q1, "answer_contents_1"));
        Q1.addAnswer(new Answer(NsUserTest.JAVAJIGI, Q1, "answer_contents_2"));

        Q1.deleteAndGetHistories(NsUserTest.JAVAJIGI);
    }

    @Test
    void 질문자와_답변자가_다른_답변이_있는_경우_삭제_불가능() {
        Q1.addAnswer(new Answer(NsUserTest.SANJIGI, Q1, "answer_contents_1"));
        Q1.addAnswer(new Answer(NsUserTest.JAVAJIGI, Q1, "answer_contents_2"));

        assertThatThrownBy(() -> Q1.deleteAndGetHistories(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void 질문_삭제_성공_시_삭제_이력_반환() {
        Q1.addAnswer(new Answer(1L, NsUserTest.JAVAJIGI, Q1, "answer_contents_2"));
        Q1.addAnswer(new Answer(2L, NsUserTest.JAVAJIGI, Q1, "answer_contents_4"));

        assertThat(Q1.deleteAndGetHistories(NsUserTest.JAVAJIGI))
                .hasOnlyElementsOfType(DeleteHistory.class);
    }
}
