package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");

    @Test
    void 질문_작성자와_답변_작성자가_다름() {
        Assertions.assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> A2.delete(NsUserTest.JAVAJIGI))
            .withMessage("답변 작성자와 질문 작성자가 일치해야 삭제할 수 있습니다.");
    }

    @Test
    void 로그인_유저와_답변_작성자가_다름() {
        Assertions.assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
            .withMessage("본인이 작성한 답변만 삭제할 수 있습니다.");
    }

    @Test
    void 질문_작성자_로그인_유저_답변_작성자_모두_같음() throws Exception {
        A1.delete(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }
}
