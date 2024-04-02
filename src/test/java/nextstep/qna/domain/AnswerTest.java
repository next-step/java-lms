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

    @DisplayName("작성자가 아닌 사람이 답변을 삭제하려고 하면 예외를 던진다")
    @Test
    void isNotOwner() {
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("이 유저는 답변을 삭제할 권한이 없습니다");
    }

    @DisplayName("대답을 삭제하면 그 상태(deleted)가 true로 바뀐다")
    @Test
    void isDeleted() throws CannotDeleteException {
        A2.delete(NsUserTest.SANJIGI);
        assertThat(A2.isDeleted()).isTrue();
    }


}
