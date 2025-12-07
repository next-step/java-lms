package nextstep.qna.domain;

import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(SANJIGI, "title2", "contents2");

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

}
