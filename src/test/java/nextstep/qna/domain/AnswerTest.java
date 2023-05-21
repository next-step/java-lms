package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answer answer;

    @BeforeEach
    public void beforeEach() {
        answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "내용1");
    }

    @DisplayName("로그인 사용자와 답변한 사람이 같은 경우 예외가 발생하지 않는지 확인")
    @Test
    void delete_로그인_사용자와_답변자가_같은_경우() {
        assertThatCode(() -> answer.delete(NsUserTest.JAVAJIGI))
                .doesNotThrowAnyException();
    }

    @DisplayName("로그인 사용자와 답변한 사람이 다른 경우 CannotDeleteException 예외가 발생하는지 확인")
    @Test
    void delete_로그인_사용자와_답변자가_다른_경우() {
        assertThatThrownBy(() -> answer.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("답변 삭제 시 삭제 상태(deleted - boolean type)로 변경되는지 확인")
    @Test
    void delete_삭제_상태_변경() throws CannotDeleteException {
        assertThat(answer.delete(NsUserTest.JAVAJIGI).isDeleted()).isEqualTo(true);
    }
}
