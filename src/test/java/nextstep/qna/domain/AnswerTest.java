package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변자가 동일하면 delete는 성공해야한다.")
    void deleteTest1() throws CannotDeleteException {
        assertThat(A1.delete(NsUserTest.JAVAJIGI)).isExactlyInstanceOf(DeleteHistory.class);
    }
    @Test
    @DisplayName("답변자가 다르다면 예외를 throw")
    void deleteTest2() {
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
