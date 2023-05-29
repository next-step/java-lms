package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");

    @DisplayName("질문자와 답변자가 다른 경우, 답변 삭제 불가능")
    @Test
    void 답변_삭제_권한_테스트() {
        Assertions.assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
                .withMessageMatching(Answer.DELETE_ANSWER_AUTHORITY);
    }
}
