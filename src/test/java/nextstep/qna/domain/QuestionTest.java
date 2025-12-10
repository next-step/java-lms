package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void validateOwner_성공() throws CannotDeleteException {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");

        question.validateOwner(NsUserTest.JAVAJIGI);
    }

    @Test
    void validateOwner_실패() {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");

        assertThatThrownBy(() -> question.validateOwner(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete() {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
        question.deleteQuestion();
        assertThat(question.isDeleted()).isTrue();
    }
}
