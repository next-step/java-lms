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

    @DisplayName("현재 로그인한 사용자와 답변자가 다르면 예외가 발생한다.")
    @Test
    void test01() {
        assertThatThrownBy(() -> A1.deleteBy(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변은 삭제할 수 없습니다.");
    }

    @DisplayName("현재 로그인 계정과 답변 작성자가 같으면 답변을 삭제한다.")
    @Test
    void test02() throws CannotDeleteException {
        A1.deleteBy(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @DisplayName("삭제 가능 여부를 확인한다.")
    @Test
    void test03() {
        assertThat(A1.isDeletableBy(NsUserTest.JAVAJIGI)).isTrue();
    }
}
