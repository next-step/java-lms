package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.qna.domain.QuestionTest.Q1;
import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");
    
    @Test
    void 답변을_삭제할_경우_삭제_상태가_변경된다() throws CannotDeleteException {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");

        answer.delete();

        assertThat(answer.isDeleted()).isTrue();
    }
    
    @Test
    void 답변을_삭제할_경우_DeleteHistory_목록을_반환한다() {
        Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, Q1, "Answers Contents1");

        DeleteHistory expected = new DeleteHistory(ContentType.ANSWER, 1L, NsUserTest.JAVAJIGI, LocalDateTime.now());
        DeleteHistory history = answer.deleteHistory();

        assertThat(history).isEqualTo(expected);
    }
}
