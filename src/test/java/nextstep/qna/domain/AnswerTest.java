package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("작성자가 다르면 삭제할 수 없다.")
    void deleteFail() {
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("작성자가 같으면 삭제할 수 있다.")
    void deleteSuccess() {
        A1.delete(NsUserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제에 성공하면 삭제이력이 남는다")
    void deleteHistory() {
        DeleteHistory history = A1.delete(NsUserTest.JAVAJIGI);

        assertThat(history).isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }
}
