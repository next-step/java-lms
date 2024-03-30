package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AnswersTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("[성공] 답변들을 삭제한다.")
    void 답변들_삭제() {
        Answers answers = new Answers(List.of(A1));

        Assertions.assertThatNoException()
                        .isThrownBy(() -> { answers.delete(NsUserTest.JAVAJIGI); });
    }

    @Test
    @DisplayName("[실패] 다른 사람이 답변을 등록한 경우 삭제할 수 없다.")
    void 답변들_삭제_불가능() {
        Answers answers = new Answers(List.of(A1, A2));

        Assertions.assertThatThrownBy(() -> {
            answers.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
