package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @DisplayName("답글 작성자와 사용자가 다르면 예외를 발생시킵니다.")
    @Test
    void notSameUser() {
        // given
        // when
        // then
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("답변을 삭제할 권한이 없습니다.");
    }

    @DisplayName("답글의 작성자와 사용자가 같을 때 삭제 상태로 바꾼다.")
    @Test
    void changeStatus() throws CannotDeleteException {
        // given
        // when
        A1.delete(NsUserTest.JAVAJIGI);
        // then
        assertThat(A1.isDeleted()).isTrue();
    }

    @DisplayName("deleteHistory 목록을 생성합니다.")
    @Test
    void makeDeleteHistory() {
        // given
        // when
        DeleteHistory deleteHistory = A1.writeDeleteHistory();
        // then
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI));
    }
}
