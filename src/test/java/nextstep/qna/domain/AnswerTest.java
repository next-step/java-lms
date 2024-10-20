package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.Answer.ANOTHER_OWNER_EXISTS_EXCEPTION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변글의 소유자가 다르면 예외를 던진다.")
    @Test
    void checkIfAnotherOwnerExistsTest() {
        assertThatThrownBy(
                () -> A1.checkIfAnotherOwnerExists(NsUserTest.SANJIGI)
        )
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining(ANOTHER_OWNER_EXISTS_EXCEPTION_MESSAGE);
    }
}
