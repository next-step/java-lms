package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer NEW_ANSWER = new Answer(NsUserTest.NEWUSER, QuestionTest.Q1, "Answers Contents3");

    @Test
    @DisplayName("삭제 메서드 테스트")
    public void deleteTest() {
        LocalDateTime deleteTime = LocalDateTime.now();

        assertThat(A1.delete(deleteTime)).isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), deleteTime));
        assertThat(A1.isDeleted()).isTrue();
    }
}
