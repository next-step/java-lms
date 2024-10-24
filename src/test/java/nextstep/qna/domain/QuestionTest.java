package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void canDelete_성공() {
        NsUser user = new NsUser(1L, "test", "123", "name", "test@naver.com");
        Question question = new Question(user, "title", "contents");
        Answer answer = new Answer(user, question, "answer");
        question.addAnswer(answer);

        assertThat(question.canDelete(user)).isTrue();
    }

    @Test
    void canDelete_실패() {
        NsUser user1 = new NsUser(1L, "test", "123", "name", "test@naver.com");
        NsUser user2 = new NsUser(1L, "test", "123", "name", "test@naver.com");
        Question question = new Question(user1, "title", "contents");
        Answer answer1 = new Answer(user1, question, "answer");
        Answer answer2 = new Answer(user2, question, "answer");
        question.addAnswer(answer1);
        question.addAnswer(answer2);

        assertThat(question.canDelete(user1)).isFalse();
    }

    @Test
    void delete() throws CannotDeleteException {
        NsUser user = new NsUser(1L, "test", "123", "name", "test@naver.com");
        Question question = new Question(user, "title", "contents");
        Answer answer = new Answer(user, question, "answer");
        question.addAnswer(answer);

        assertThat(question.delete(user)).hasSize(2);
    }
}
