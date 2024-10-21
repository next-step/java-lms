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
    void delete() throws CannotDeleteException {
        List<DeleteHistory> deleteHistory = Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(deleteHistory).containsExactly(
                new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()));
    }

    @Test
    void delete_question_with_answer() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title", "content");
        Answer answer = new Answer(NsUserTest.JAVAJIGI, question, "content");
        question.addAnswer(answer);
        List<DeleteHistory> deleteHistories = question.delete(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistories).containsExactlyInAnyOrder(
                deleteHistory(ContentType.QUESTION, question.getId(), question.getWriter()),
                deleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter()));
    }

    @Test
    void throw_exception_if_delete_question_with_other_user_answer() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title", "content");
        question.addAnswer(new Answer(NsUserTest.SANJIGI, question, "content"));

        assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    private DeleteHistory deleteHistory(ContentType contentType, Long contentId, NsUser writer) {
        return new DeleteHistory(contentType, contentId, writer, LocalDateTime.now());
    }

    @Test
    void throw_exception_if_login_user_not_match() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
