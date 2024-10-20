package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("삭제 요창자와 답변 작성자가 다르면 예외가 발생한다.")
    void shouldThrowExceptionWhenDeleterIsNotSameAsAnswerAuthor() {
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
            .hasMessage("자신의 답변이 아닌 경우 삭제가 불가능합니다.")
            .isExactlyInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제 요청자와 답변 작성자가 같은 경우 삭제가 가능하다.")
    void shouldAllowDeletionWhenDeleterIsSameAsAnswerAuthor() {
        A1.delete(NsUserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제가 된 경우 DeleteHistory 객체를 반환한다.")
    void shouldReturnDeleteHistoryWhenPostIsDeleted() {
        final DeleteHistory deleteHistory = A1.delete(NsUserTest.JAVAJIGI);

        assertThat(deleteHistory)
            .isEqualTo(
                new DeleteHistory(
                    ContentType.ANSWER,
                    A1.getId(),
                    NsUserTest.JAVAJIGI,
                    LocalDateTime.now()
                )
            );
    }
}
