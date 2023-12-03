package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.QuestionTest.Q1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");

    @Test
    @DisplayName("삭제를 할 경우 답변의 삭제 상태를 변경한다.")
    void 답변_상태_변경() {
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문자와 답변자가 같은 경우 삭제가 가능하다.")
    void 질문자와_답변자가_같으면_답변_삭제() {
        A1.deleteBy(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문자와 답변자가 다른 경우 예외를 반환한다.")
    void 질문자와_답변자가_다르면_예외반환() {
        assertThatThrownBy(() -> A1.deleteBy(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

}
