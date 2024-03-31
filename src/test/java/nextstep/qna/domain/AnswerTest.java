package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answer A1_CLONE;

    @BeforeEach
    void setUp() {
        A1_CLONE = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    @Test
    @DisplayName("답변 작성자면 삭제가 가능")
    void delete_when_owner() throws CannotDeleteException {
        A1_CLONE.delete(NsUserTest.JAVAJIGI);
        assertThat(A1_CLONE).isEqualTo(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1", true));
    }

    @Test
    @DisplayName("답변 작성자가 아니면 삭제가 불가능")
    void delete_when_not_owner() {
        assertThatThrownBy(() -> {
            A1.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
