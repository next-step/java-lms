package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변을 생성하면 질문의 답변 리스트에 들어간다.")
    void addAnswer() {
        Answer newAnswer = new Answer(12L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "answer");
        assertThat(QuestionTest.Q1.getAnswers()).contains(newAnswer);
    }

    @Test
    @DisplayName("답변을 삭제하면, 답변 삭제 히스토리를 반환한다.")
    void deleteAndReturnHistory() {
        assertThat(A1.delete()).isNotNull()
                .hasFieldOrPropertyWithValue("contentType", ContentType.ANSWER)
                .hasFieldOrPropertyWithValue("deletedBy", NsUserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("답변을 삭제하면, 삭제 상태가 변경된다.")
    void deleteAndUpdateDeleteStatus() {
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();
    }
}
