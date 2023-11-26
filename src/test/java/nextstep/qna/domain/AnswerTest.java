package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("소유자가 다르면 질문을 삭제 할 수 없다.")
    void validateDeleteOwner() {
        Assertions.assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
