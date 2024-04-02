package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.exception.CannotDeleteExceptionMessage.CAN_DELETE_ONLY_ANSWER_OWNER;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class AnswersTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("[성공] 답변들을 삭제한다.")
    void 답변들_삭제() {
        Answers answers = new Answers(List.of(A1));
        assertThatNoException()
                        .isThrownBy(() -> { answers.delete(NsUserTest.JAVAJIGI); });
    }

    @Test
    @DisplayName("[실패] 다른 사람이 등록한 답변을 삭제하려는 경우 CannotDeleteException 예외가 발생한다.")
    void 답변들_삭제_불가능() {
        Answers answers = new Answers(List.of(A1, A2));

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> answers.delete(NsUserTest.JAVAJIGI))
                .withMessageContaining(CAN_DELETE_ONLY_ANSWER_OWNER.getMessage());
    }

}
