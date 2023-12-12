package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswerTest {
    private final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    private final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 답변삭제권한_exception() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> A2.delete(NsUserTest.JAVAJIGI))
                .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void delete() throws CannotDeleteException {
        assertThat(A1.delete(NsUserTest.JAVAJIGI)).isEqualTo(DeleteHistory.ofAnswer(A1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()));
        assertThat(A1.isDeleted()).isTrue();
    }
}
