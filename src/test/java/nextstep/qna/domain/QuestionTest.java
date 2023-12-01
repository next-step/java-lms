package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.fixture.NsUserFixture.*;
import static org.assertj.core.api.Assertions.*;

public class QuestionTest {

    private static final String TEST_TITLE = "Test Title";
    private static final String TEST_CONTENT = "Test Content";
    private static final LocalDateTime NOW = LocalDateTime.now();

    @DisplayName("질문자와 로그인 사용자가 동일하지 않다면 삭제할 수 없다.")
    @Test
    void if_user_is_not_same_as_login_user_then_can_not_delete_question() {
        Question givenQuestion = new Question(JAVAJIGI, TEST_TITLE, TEST_CONTENT);
        NsUser loginUser = SANJIGI;

        assertThatThrownBy(() -> givenQuestion.delete(loginUser))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("답변이 없고 질문만 존재할 경우 삭제할 수 있다.")
    @Test
    void should_delete_if_question_has_no_answer() {
        NsUser loginUser = JAVAJIGI;
        Question givenQuestion = new Question(JAVAJIGI, TEST_TITLE, TEST_CONTENT);
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION,
                                                        givenQuestion.getId(),
                                                        loginUser,
                                                        NOW);
        DeleteHistories expected = new DeleteHistories(List.of(deleteHistory));

        DeleteHistories actual = givenQuestion.delete(loginUser);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("질문자와 답변자가 동일하다면 질문 데이터와 답변 데이터 모두 삭제된다.")
    @Test
    void delete_question_and_answers_if_login_user_is_owner() {
        NsUser loginUser = JAVAJIGI;
        Question givneQuestion = new Question(JAVAJIGI, TEST_TITLE, TEST_CONTENT);
        Answer givenAnswer1 = new Answer(JAVAJIGI, givneQuestion, TEST_CONTENT);
        Answer givenAnswer2 = new Answer(JAVAJIGI, givneQuestion, TEST_CONTENT);
        givneQuestion.addAnswer(givenAnswer1);
        givneQuestion.addAnswer(givenAnswer2);
        DeleteHistory deletedQuestion = new DeleteHistory(ContentType.QUESTION,
                                                          givneQuestion.getId(),
                                                          loginUser,
                                                          NOW);
        DeleteHistory deletedAnswer1 = new DeleteHistory(ContentType.ANSWER,
                                                        givenAnswer1.getId(),
                                                        loginUser,
                                                        NOW);
        DeleteHistory deletedAnswer2 = new DeleteHistory(ContentType.ANSWER,
                                                        givenAnswer1.getId(),
                                                        loginUser,
                                                        NOW);
        DeleteHistories expected = new DeleteHistories(List.of(deletedQuestion, deletedAnswer1, deletedAnswer2));

        DeleteHistories actual = givneQuestion.delete(loginUser);

        assertThat(actual).isEqualTo(expected);
    }
}
