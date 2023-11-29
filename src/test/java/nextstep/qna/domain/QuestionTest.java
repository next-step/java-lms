package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.fixture.AnswerFixture.*;
import static nextstep.fixture.NsUserFixture.*;
import static nextstep.fixture.QuestionFixture.*;
import static org.assertj.core.api.Assertions.*;

public class QuestionTest {

    private Question question;

    private NsUser loginUser;

    @BeforeEach
    void setUp() {
        question = new Question(Q1.getId(), Q1.getWriter(), Q1.getTitle(), Q1.getContents());
        loginUser = Q1.getWriter();
    }

    @DisplayName("질문자와 로그인 사용자가 동일하지 않다면 삭제할 수 없다.")
    @Test
    void if_user_is_not_same_as_login_user_then_can_not_delete_question() {
        assertThatThrownBy(() -> question.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("답변이 없고 질문만 존재할 경우 삭제할 수 있다.")
    @Test
    void should_delete_if_question_has_no_answer() {
        DeleteHistories expected = new DeleteHistories(List.of(new DeleteHistory(ContentType.QUESTION,
                                                                                 Q1.getId(),
                                                                                 question.getWriter(),
                                                                                 LocalDateTime.now())));

        DeleteHistories actual = question.delete(loginUser);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("질문자와 답변자가 동일하다면 질문 데이터와 답변 데이터 모두 삭제된다.")
    @Test
    void delete_question_and_answers_if_login_user_is_owner() {
        List<DeleteHistory> givenQuestionsAndAnswers = List.of(new DeleteHistory(ContentType.QUESTION,
                                                                                 Q1.getId(),
                                                                                 JAVAJIGI,
                                                                                 LocalDateTime.now()),
                                                               new DeleteHistory(ContentType.ANSWER,
                                                                                 A1.getId(),
                                                                                 question.getWriter(),
                                                                                 LocalDateTime.now()),
                                                               new DeleteHistory(ContentType.ANSWER,
                                                                                 A3.getId(),
                                                                                 question.getWriter(),
                                                                                 LocalDateTime.now()));
        question.addAnswer(new Answer(A1.getId(), A1.getWriter(), question, A1.getContents()));
        question.addAnswer(new Answer(A3.getId(), A3.getWriter(), question, A3.getContents()));
        DeleteHistories expected = new DeleteHistories(givenQuestionsAndAnswers);

        DeleteHistories actual = question.delete(loginUser);

        assertThat(actual).isEqualTo(expected);
    }
}
