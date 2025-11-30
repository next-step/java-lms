package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 본인_질문_삭제_성공() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title", "contents");
        question.deleteBy(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    void 다른사람_질문_삭제시_예외() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title", "contents");
        assertThatThrownBy(() -> question.deleteBy(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 본인_답변만_있으면_삭제_성공() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title", "contents");
        question.add(new Answer(NsUserTest.JAVAJIGI, question, "answer"));
        question.deleteBy(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    void 다른사람_답변이_있으면_삭제_실패() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title", "contents");
        question.add(new Answer(NsUserTest.SANJIGI, question, "answer"));
        assertThatThrownBy(() -> question.deleteBy(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 답변은_본인이지만_질문이_타인이면_삭제_실패() {
        Question question = new Question(NsUserTest.SANJIGI, "title", "contents");
        question.add(new Answer(NsUserTest.JAVAJIGI, question, "answer"));
        assertThatThrownBy(() -> question.deleteBy(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 삭제_기록_생성() throws CannotDeleteException {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        question.add(new Answer(NsUserTest.JAVAJIGI, question, "answer"));
        question.deleteBy(NsUserTest.JAVAJIGI);
        assertThat(question.createDeleteHistories()).hasSize(2);
    }
}
