package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변을 삭제할 수 있다")
    public void delete_answer() {
        A1.deleted();
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변 삭제 시 히스토리를 생성할 수 있다")
    public void delete_answer_history() {
        assertThat(A1.deleted()).isInstanceOf(DeleteHistory.class);
    }

}
