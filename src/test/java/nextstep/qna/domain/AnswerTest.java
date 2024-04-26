package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 삭제() {
        DeleteHistory deleteHistory = A1.delete();

        assertThat(A1.isDeleted()).isEqualTo(true);
        assertThat(deleteHistory).isEqualTo(DeleteHistory.createAnswer(A1.getId(), A1.getWriter(), LocalDateTime.now()));
    }
}
