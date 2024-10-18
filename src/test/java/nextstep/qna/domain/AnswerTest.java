package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변자와 질문자 다름")
    void delete_validation() {
        assertThatThrownBy(()-> A2.delete(NsUserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class).hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("답변자와 질문자 같은 사람_답변 정상 삭제")
    void delete_normal() throws CannotDeleteException {
        DeleteHistory expected = new DeleteHistory(ContentType.ANSWER, A2.getId(), NsUserTest.SANJIGI, LocalDateTime.now());
        assertThat(A2.delete(NsUserTest.SANJIGI)).isEqualTo(expected);
    }

}
