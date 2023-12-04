package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q2, "Answers Contents3");

    @DisplayName("본인 답변이 아닌데 삭제하려고 할 경우 예외발생 -> 삭제불가")
    @Test
    void 다른사람_답변을_삭제하려면_예외발생() {
        assertThatThrownBy(() -> A1.remove(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("답변을 삭제할 권한이 없습니다.");
    }

    @DisplayName("답변을 삭제하면 이력 리턴")
    @Test
    void 답변_삭제() throws CannotDeleteException {
        assertThat(A3.remove(NsUserTest.SANJIGI))
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, A3.getId(), NsUserTest.SANJIGI, LocalDateTime.now()));
    }
}
