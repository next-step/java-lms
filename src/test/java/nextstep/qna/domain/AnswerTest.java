package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.answer.Answer;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void isDeleted_예외_케이스() {
        Assertions.assertThatThrownBy(() -> A1.deletedBy(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void isDeleted_정상_케이스() throws CannotDeleteException {
        A1.deletedBy(NsUserTest.JAVAJIGI);
        Assertions.assertThat(A1.isDeleted()).isTrue();
    }
}
