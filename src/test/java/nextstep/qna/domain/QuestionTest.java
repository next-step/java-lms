package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void delete_성공() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.delete(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    void delete_다른_사람이_쓴_글() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        assertThatThrownBy(() -> question.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_성공_질문자_답변자_같음() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(AnswerTest.A1);
        question.delete(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    void delete_답변_중_다른_사람이_쓴_글() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(AnswerTest.A1);
        question.addAnswer(AnswerTest.A2);

        assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
