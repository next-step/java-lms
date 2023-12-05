package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변을 삭제하면 삭제 히스토리에 담긴다")
    @Test
    void changeDeleted() {
        Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        DeleteHistory deleteHistory = answer.deleteAnswer(NsUserTest.JAVAJIGI);

        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, 1L, NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @DisplayName("답변을 삭제할 떄 로그인된 유저와 답변의 작성자가 아닐 경우 예외를 던진다")
    @Test
    void deleteAnswerByWrongUser() {
        assertThatThrownBy(() -> A1.deleteAnswer(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
