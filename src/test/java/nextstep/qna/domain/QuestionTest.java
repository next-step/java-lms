package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 질문은_삭제하면_삭제상태가_변경된다() throws CannotDeleteException {
        Question question = new Question(NsUser.GUEST_USER, "title1", "content");
        question.delete(NsUser.GUEST_USER);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    void 질문은_올린사람만_삭제_가능하다() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 질문을_삭제하면_삭제히스토리가_나온다() throws CannotDeleteException {
        LocalDateTime time = LocalDateTime.now();
        DeleteHistory history = new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), time);
        assertThat(Q1.delete(NsUserTest.JAVAJIGI)).contains(history);
    }

    @Test
    void 답변이_없는_경우_삭제_가능하다() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void 질문을_삭제하면_답변도_삭제된다() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(AnswerTest.A1.isDeleted()).isTrue();
    }

    @Test
    void 답변이_삭제되는_경우_해당하는_응답도_노출된다() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        LocalDateTime time = LocalDateTime.now();
        DeleteHistory questionHistory = new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), time);
        DeleteHistory answerHistory = new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), time);

        assertThat(Q1.delete(NsUserTest.JAVAJIGI)).contains(questionHistory, answerHistory);
    }

}
