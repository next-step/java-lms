package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 답변은_삭제를_하면_삭제_상태값이_변한다() throws CannotDeleteException {
        A1.delete(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    void 답변은_자기가_작성한것만_삭제할_수_있다() {
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 답변을_삭제하면_삭제히스토리가_나온다() throws CannotDeleteException {
        LocalDateTime time = LocalDateTime.now();
        DeleteHistory history = new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), time);
        assertThat(A1.delete(NsUserTest.JAVAJIGI, time)).isEqualTo(history);
    }
}
