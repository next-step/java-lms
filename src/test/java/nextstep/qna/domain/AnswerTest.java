package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.fixture.NsUserFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {

    private static final String TEST_TITLE = "test_title";
    private static final String TEST_CONTENT = "test_content";

    @DisplayName("다른 사람이 쓴 답변이 있다면 예외가 발생한다.")
    @Test
    void if_user_is_not_same_as_login_user_then_can_not_delete_question() {
        Question givenQuestion = new Question(JAVAJIGI, TEST_TITLE, TEST_CONTENT);
        Answer givenAnswer = new Answer(JAVAJIGI, givenQuestion, TEST_CONTENT);

        assertThatThrownBy(() -> givenAnswer.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문 데이터는 삭제 후 soft delete 처리가 된다.")
    @Test
    void set_delete_true_when_answer_was_deleted() {
        Question givenQuestion = new Question(JAVAJIGI, TEST_TITLE, TEST_CONTENT);
        Answer givenAnswer = new Answer(JAVAJIGI, givenQuestion, TEST_CONTENT);
        DeleteHistory expected = new DeleteHistory(ContentType.ANSWER,
                                                   givenAnswer.getId(),
                                                   givenAnswer.getWriter(),
                                                   LocalDateTime.now());

        DeleteHistory actual = givenAnswer.delete(JAVAJIGI);

        assertThat(givenAnswer.isDeleted()).isTrue();
        assertThat(actual).isEqualTo(expected);
    }
}
