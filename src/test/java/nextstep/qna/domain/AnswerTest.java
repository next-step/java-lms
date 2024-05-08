package nextstep.qna.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답글 삭제 시 삭제 상태가 변경되어야 한다.")
    void CANNOT_DELETE_IF_DIFF_LOG_USER_AND_WRITER() {
        NsUser logUser = NsUserTest.SANJIGI;
        A1.delete(logUser);
        Assertions.assertThat(A1.isDeleted()).isEqualTo(true);
    }
}
