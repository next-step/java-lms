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

    @DisplayName("다른 사람이 쓴 답변이 있으면 삭제할 수 없다.")
    @Test
    public void shouldNotDeleteIfAnyAnswerThatWrittenByOther() {
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("답글을 삭제하면 올바른 이력을 반환한다.")
    @Test
    public void shouldReturnProperDeleteHistory() throws CannotDeleteException {
        DeleteHistory delete = A1.delete(NsUserTest.JAVAJIGI);
        assertThat(delete).isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }
}
