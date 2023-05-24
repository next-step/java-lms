package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.qna.domain.ContentType.ANSWER;
import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 삭제 시, DeleteHistory 반환")
    void delete_then_return_DeleteHistory() {
        assertThat(A1.delete(LocalDateTime.now()))
                .isEqualTo(new DeleteHistory(ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
    }
}
