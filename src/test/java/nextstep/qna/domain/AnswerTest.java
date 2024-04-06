package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q2, "Answers Contents3");
    public static final Answer A4 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q2, "Answers Contents3");

    @Test
    @DisplayName("답변 삭제 성영")
    public void delete_success() {
        assertThat(A1.isDeleted()).isFalse();
        DeleteHistory deleteHistory = A1.delete();

        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleteHistory).isNotNull();
    }
}
