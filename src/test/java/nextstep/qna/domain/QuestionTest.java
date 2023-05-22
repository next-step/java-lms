package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class QuestionTest {
    @Test
    void 작성자가_아닌_유저가_question을_지우려_하면_예외가_발생한다() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        assertThatThrownBy(() -> question.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void question을_지우면_answer들도_포함한_deleteHistory_list가_반환된다() throws CannotDeleteException {
        Question question = new Question(NsUserTest.SANJIGI, "title2", "contents2");
        Answer answer = new Answer(NsUserTest.SANJIGI, question, "Answers Contents2");
        question.addAnswer(answer);

        assertThat(question.delete(NsUserTest.SANJIGI).size()).isEqualTo(2);
    }

    @Test
    void question을_지우면_deleted는_true가_된다() throws CannotDeleteException {
        Question question = new Question(NsUserTest.SANJIGI, "title2", "contents2");
        assertThat(question.isDeleted()).isFalse();

        question.delete(NsUserTest.SANJIGI);
        assertThat(question.isDeleted()).isTrue();
    }
}
