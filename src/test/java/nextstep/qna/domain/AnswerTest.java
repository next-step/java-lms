package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("자신의 답변이면 삭제가 가능하다.")
    void deleteAnswerTest() {
        A1.delete(NsUserTest.JAVAJIGI);
        Assertions.assertThat(A1.isDeleted()).isEqualTo(true);
    }


}
