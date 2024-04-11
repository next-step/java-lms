package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문 및 정답 삭제 테스트")
    void testDeleteQuestion() throws CannotDeleteException {
        NsUser writer = NsUserTest.JAVAJIGI;
        int answerLength = 5;
        Question question = makeQuestion(writer);
        Answers answers = makeAnswers(writer, question, answerLength);
        addAnswers(question, answers);

        assertDeleteQuestionAndAnswers(question, answerLength, writer);
    }

    @Test
    @DisplayName("이미 삭제된 질문 삭제할 경우 에러 발생")
    void testDeleteQuestionAlreadyDeleted() throws CannotDeleteException {
        NsUser writer = NsUserTest.JAVAJIGI;
        int answerLength = 5;
        Question question = makeQuestion(writer);
        Answers answers = makeAnswers(writer, question, answerLength);
        addAnswers(question, answers);

        question.delete(writer);
        assertThatThrownBy(() -> question.delete(writer))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문 작성자와 로그인 유저가 다른 경우 에러 발생")
    void testNotAuthorQuestion() throws CannotDeleteException {
        NsUser questionWriter = NsUserTest.JAVAJIGI;
        NsUser loginUser = NsUserTest.SANJIGI;
        int answerLength = 5;

        Question question = makeQuestion(questionWriter);
        Answers answers = makeAnswers(loginUser, question, answerLength);
        addAnswers(question, answers);

        assertThatThrownBy(() -> question.delete(loginUser))
                .isInstanceOf(CannotDeleteException.class);
    }

    private void assertDeleteQuestionAndAnswers(Question question, int answerLength, NsUser loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = question.delete(loginUser);
        assertThat(deleteHistories).hasSize(answerLength + 1);

        assertThat(question.isDeleted()).isTrue();
        assertThat(question.getAnswers()).extracting(Answer::isDeleted).containsOnly(true);
    }

    private Question makeQuestion(NsUser user) {
        return new Question(user, "test", "test");
    }

    private Answer makeAnswer(NsUser nsUser, Question question) {
        return new Answer(nsUser, question, "test");
    }

    private Answers makeAnswers(NsUser nsUser, Question question, int length) {
        Answers answers = new Answers();

        for (int i = 0; i < length; i++) {
            answers.add(makeAnswer(nsUser, question));
        }

        return answers;
    }

    private void addAnswers(Question question, Answers answers) {
        answers.getAnswers().forEach(question::addAnswer);
    }
}
