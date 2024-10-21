package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 답변을_삭제한다() {
        LocalDateTime deleteDateTime = LocalDateTime.of(2024,10,21,10,10);
        DeleteHistory deleteHistory = A1.delete(NsUserTest.JAVAJIGI, deleteDateTime);

        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), NsUserTest.JAVAJIGI, deleteDateTime));
    }

    @Test
    void 작성자가_다르면_삭제_실패한다() {
        LocalDateTime deleteDateTime = LocalDateTime.of(2024,10,21,10,10);
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI, deleteDateTime))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
