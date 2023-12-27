package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.fixtures.NsUserFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    private static final Question Q1 = new Question(NsUserFixtures.TEACHER_JAVAJIGI_1L, "title1", "contents1");
    private static final Answer A1 = new Answer(NsUserFixtures.TEACHER_JAVAJIGI_1L, Q1, "Answers Contents1");
    private static final Answer A2 = new Answer(NsUserFixtures.TEACHER_SANJIGI_2L, Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 작성자가 로그인한 사용자면 답변을 삭제 상태로 변경하고 해당 답변을 반환한다")
    void setDeleted_success() throws CannotDeleteException {
        Answer deletedAnswer = A1.delete(NsUserFixtures.TEACHER_JAVAJIGI_1L);

        assertThat(deletedAnswer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문 작성자가 로그인한 사용자가 아니면 삭제할 수 없다는 예외를 던진다")
    void setDeleted_different_answerWriter_loginUser_throwsException() {
        assertThatThrownBy(
                () -> A2.delete(NsUserFixtures.TEACHER_JAVAJIGI_1L)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
