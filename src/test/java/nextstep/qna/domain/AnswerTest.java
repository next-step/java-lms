package nextstep.qna.domain;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");

    @DisplayName("답변 작성자가 로그인 사용자일 경우 삭제 가능하다")
    @Test
    void shouldNotThrow_whenUserIsOwner() {
        assertThatCode(() -> A1.delete(JAVAJIGI))
                .doesNotThrowAnyException();
    }

    @DisplayName("답변 중 다른 사람이 쓴 답변이 있는 경우 삭제 불가하다")
    @Test
    void shouldThrow_whenUserAndWriterDifferent() {
        assertThatThrownBy(() -> A1.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("답변 작성자가 삭제할 경우 상태가 변경된다")
    @Test
    void shouldChangeDeletedStatus_whenDeleteByOwner() throws CannotDeleteException {
        A1.delete(JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @DisplayName("삭제 이력을 반환한다")
    @Test
    void returnDeleteHistory() throws CannotDeleteException {
        assertThat(A1.toDeleteHistories()).hasSize(1);
    }

}
