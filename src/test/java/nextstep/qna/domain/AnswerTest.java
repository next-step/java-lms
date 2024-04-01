package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.qna.exception.CannotDeleteExceptionMessage.CAN_DELETE_ONLY_ANSWER_OWNER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("[성공] 자신이 작성한 답변을 삭제한다.")
    void 답변_삭제() throws CannotDeleteException {
        assertThat(A1.delete(NsUserTest.JAVAJIGI).isDeleted()).isTrue();
    }

    @Test
    @DisplayName("[실패] 자신이 생성하지 않은 답변을 삭제하려는 경우 CannotDeleteException 예외가 발생한다.")
    void 답변_삭제_불가능() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
                .withMessageContaining(CAN_DELETE_ONLY_ANSWER_OWNER.getMessage());
    }

}
