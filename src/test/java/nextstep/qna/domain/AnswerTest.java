package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 답변_삭제시_상태를_변경하고_삭제이력을_반환한다() {
        final var actual = A1.deleteAnswer(NsUserTest.JAVAJIGI);

        assertAll(
                () -> assertThat(A1.isDeleted()).isTrue(),
                () -> assertNotNull(actual)
        );
    }

    @Test
    void 답변_삭제시_질문자와_답변자가_다를경우_예외를_던진다() {
        assertThrows(
                CannotDeleteException.class,
                () -> A1.deleteAnswer(NsUserTest.SANJIGI)
        );
    }
}
