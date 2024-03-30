package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("Answer가 본인이 작성하지 않으면 삭제할 수 없다.")
    void isNotMyAnswer() {
        assertThatThrownBy(() -> A1.validateDeletable(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("본인이 작성한 답변은 삭제할 수 있다.")
    void isMyAnswer() {
        assertThatNoException().isThrownBy(() -> A1.validateDeletable(NsUserTest.JAVAJIGI));
    }
}
