package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.fixture.NsUserFixture.*;
import static org.assertj.core.api.Assertions.*;

class QuestionTest {

    private static final String TEST_TITLE = "test_title";
    private static final String TEST_CONTENTS = "test_contents";

    @DisplayName("질문 소유자가 아닌 사용자가 질문을 삭제할 경우 예외가 발생한다.")
    @Test
    void if_user_is_not_question_owner_then_throw_exception() {
        QuestionTexts questionTexts = new QuestionTexts(TEST_TITLE, TEST_CONTENTS);
        Times times = new Times(LocalDateTime.now(), null);
        QuestionInformation information = new QuestionInformation(questionTexts, JAVAJIGI, times, false);
        Question question = new Question(1L, information);
        NsUser loginUser = SANJIGI;

        assertThatThrownBy(() -> question.delete(loginUser))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문 소유자는 질문을 삭제할 수 있다.")
    @Test
    void if_user_is_question_owner_then_delete_question() {
        QuestionTexts questionTexts = new QuestionTexts(TEST_TITLE, TEST_CONTENTS);
        Times times = new Times(LocalDateTime.now(), null);
        QuestionInformation information = new QuestionInformation(questionTexts, JAVAJIGI, times, false);
        Question question = new Question(1L, information);
        NsUser loginUser = JAVAJIGI;

        Question deletedQuestion = question.delete(loginUser);
        boolean actual = deletedQuestion.isDeleted();

        assertThat(actual).isTrue();
    }

    @DisplayName("질문과 답변 소유자가 동일해야 모두 삭제할 수 있다.")
    @Test
    void if_question_and_answers_owner_is_same_then_delete_question_and_answers() {
        QuestionTexts questionTexts = new QuestionTexts(TEST_TITLE, TEST_CONTENTS);
        Times times = new Times(LocalDateTime.now(), null);
        QuestionInformation information = new QuestionInformation(questionTexts, JAVAJIGI, times, false);
        Answer answer = new Answer(1L, new AnswerInformation(TEST_CONTENTS, JAVAJIGI, false, times));
        Answers answers = new Answers(answer);
        Question question = new Question(1L, information, answers);
        NsUser loginUser = JAVAJIGI;

        Question deletedQuestion = question.delete(loginUser);
        List<DeletedHistory> actual = deletedQuestion.buildHistories();

        assertThat(actual).hasSize(2);
    }

    @DisplayName("질문과 답변 소유자가 동일하지 않으면 삭제시 예외가 발생한다.")
    @Test
    void throw_exception_when_delete_operation_if_question_and_answers_owner_is_not_same() {
        QuestionTexts questionTexts = new QuestionTexts(TEST_TITLE, TEST_CONTENTS);
        Times times = new Times(LocalDateTime.now(), null);
        QuestionInformation information = new QuestionInformation(questionTexts, JAVAJIGI, times, false);
        Answer answer = new Answer(1L, new AnswerInformation(TEST_CONTENTS, SANJIGI, false, times));
        Answers answers = new Answers(answer);
        Question question = new Question(1L, information, answers);
        NsUser loginUser = JAVAJIGI;

        assertThatThrownBy(() -> question.delete(loginUser))
                .isInstanceOf(CannotDeleteException.class);
    }
}
