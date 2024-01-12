package nextstep.qna.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("질문자와 답변자가 다른경우 답변을 삭제할 수 없다.")
    void test1() {
        String expectingMessage = "답변을 삭제할 권한이 없습니다.";

        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> A1.verifyIfErasable(NsUserTest.SANJIGI))
            .withMessageMatching(expectingMessage);
    }
}
