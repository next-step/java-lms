package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q2, "Answers Contents3");
    public static final Answer A4 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q2, "Answers Contents4");

    @Test
    @DisplayName("질문 작성자와 답변 작성자가 같으면 답변을 삭제 상태로 변경한다")
    void setDeleted_success() throws CannotDeleteException {
        Answer deletedAnswer = A1.setDeleted(NsUserTest.JAVAJIGI, true);

        assertThat(deletedAnswer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문 작성자와 답변 작성자가 다르면 삭제할 수 없다는 예외를 던진다")
    void setDeleted_different_questionWriter_AnswerWriter_throwsException() {
        assertThatThrownBy(
                () -> A1.setDeleted(NsUserTest.SANJIGI, true)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
