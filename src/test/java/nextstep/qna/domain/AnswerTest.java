package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 답변_작성자가_자신일_경우_예외가_발생하지_않는다() throws CannotDeleteException {
        A1.validateOwnership(NsUserTest.JAVAJIGI);
    }

    @Test
    void 답변_작성자가_타인일_경우_예외가_발생한다() {
        Assertions.assertThatThrownBy(() -> A1.validateOwnership(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
