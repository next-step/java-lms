package nextstep.qna.domain;

import static nextstep.qna.domain.QuestionContentTest.QC1;
import static nextstep.qna.domain.QuestionContentTest.QC2;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(JAVAJIGI, QC1);
    public static final Question Q2 = new Question(SANJIGI, QC2);

    @DisplayName("질문 작성자는 자신의 질문을 삭제할 수 있다")
    @Test
    void shouldNotThrow_whenUserIsOwnerAndNoOtherAnswers() {
        assertThatCode(() -> Q2.delete(SANJIGI))
                .doesNotThrowAnyException();
    }

    @DisplayName("질문자와 로그인 사용자가 다른 경우 삭제 불가하다")
    @Test
    void shouldThrow_whenUserAndWriterDifferent() {
        assertThatThrownBy(() -> Q1.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다");
    }

    @DisplayName("삭제 가능한 경우 상태가 변경된다")
    @Test
    void shouldChangeDeletedStatus_whenDeleteByOwner() throws CannotDeleteException {
        Q2.delete(SANJIGI);
        assertThat(Q2.isDeleted()).isTrue();
    }

    @DisplayName("삭제 시 삭제 이력을 반환한다")
    @Test
    void shouldReturnDeleteHistory_whenDeletePossible() throws CannotDeleteException {
        assertThat(Q1.toDeleteHistories()).hasSize(1);
    }
}
