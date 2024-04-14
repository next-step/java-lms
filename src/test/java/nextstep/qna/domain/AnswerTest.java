package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.UserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.Answer.ANSWER_DELETE_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변 삭제 기능 테스트")
    @Test
    void answer_delete_test() throws CannotDeleteException {
        A1.delete(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();

    }

    @DisplayName("답변 삭제 에러 테스트")
    @Test
    void answer_delete_error_test() throws CannotDeleteException {
        assertThatThrownBy(() -> {
            A1.delete(UserTest.SANJIGI);
        }).hasMessageContaining(ANSWER_DELETE_ERROR_MESSAGE);
    }
}
