package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("작성자는 답변을 삭제할 수 있다.")
    void deleteBy_owner_success() throws CannotDeleteException {
        DeleteHistory history = A1.deleteBy(NsUserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
        assertThat(history.isCreatedBy(NsUserTest.JAVAJIGI)).isTrue();
        assertThat(history.isForAnswer()).isTrue();
    }

    @Test
    @DisplayName("작성자가 아닌 사용자가 삭제하면 CannotDeleteException 예외가 발생한다.")
    void deleteBy_notOwner_fail() {
        assertThatThrownBy(() -> A1.deleteBy(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
