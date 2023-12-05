package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    private static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    private static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
    private static final Answer A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 작성자가 로그인한 사용자면 답변을 삭제 상태로 변경하고 해당 답변을 반환한다")
    void setDeleted_success() throws CannotDeleteException {
        Answer deletedAnswer = A1.delete(NsUserTest.JAVAJIGI);

        assertThat(deletedAnswer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문 작성자가 로그인한 사용자가 아니면 삭제할 수 없다는 예외를 던진다")
    void setDeleted_different_answerWriter_loginUser_throwsException() {
        assertThatThrownBy(
                () -> A2.delete(NsUserTest.JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
