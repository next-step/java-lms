package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 삭제 성공")
    public void delete_성공() throws CannotDeleteException {
        DeleteHistory deleteHistory = A1.delete(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
    }

    @Test
    @DisplayName("권한이 없는 경우 답변 삭제 실패")
    public void delete_실패() throws CannotDeleteException {
        assertThatThrownBy(() -> A2.delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("답변을 삭제할 권한이 없습니다.");
    }
}
