package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    private Question question;

    @BeforeEach
    public void setUp() {
        question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, question, "answer contents"));
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, question, "answer contents2"));
    }

    @Test
    void delete_질문을_삭제하면_삭제상태가_true가_된다() throws CannotDeleteException {
        question.delete(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    void delete_질문을_삭제하면_답변_삭제상태가_true가_된다() throws CannotDeleteException {
        question.delete(NsUserTest.JAVAJIGI);

        question.getAnswers()
                .getValue()
                .forEach(answer -> assertThat(answer.isDeleted()).isTrue());
    }

    @Test
    void delete_답변이_있는_질문을_삭제하면_질문과_답변_삭제_이력들을_반환한다() throws CannotDeleteException {
        List<DeleteHistory> histories = question.delete(NsUserTest.JAVAJIGI);

        assertThat(histories).hasSize(3);
    }

    @Test
    void delete_답변이_없는_질문을_삭제하면_질문_삭제_이력을_반환한다() throws CannotDeleteException {
        List<DeleteHistory> histories = new Question(NsUserTest.JAVAJIGI, "", "").delete(NsUserTest.JAVAJIGI);

        assertThat(histories).hasSize(1);
    }

    @Test
    void delete_삭제_권한이_없는_사용자가_삭제하면_에러가_발생한다() {
        assertThatThrownBy(() -> question.delete(new NsUser())).isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }
}
