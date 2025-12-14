package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void delete_성공() throws CannotDeleteException {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");

        question.delete(NsUserTest.JAVAJIGI);
    }

    @Test
    void delete_실패() {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");

        assertThatThrownBy(() -> question.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void deleteHistory() {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");

        DeleteHistory history = question.deleteHistory();

        assertThat(history).isEqualTo(DeleteHistory.from(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
    }
}
