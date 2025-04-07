package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void 작성자가_답변을_삭제한다() throws Exception {
        A1.deleteBy(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    public void 작성자가_아니면_답변_삭제시_예외가_발생한다() {
        assertThatThrownBy(() -> A2.deleteBy(NsUserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
