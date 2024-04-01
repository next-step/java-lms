package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("[성공] 자신이 작성한 질문을 삭제한다.")
    void 답변_삭제() throws CannotDeleteException {
        assertThat(Q1.delete(NsUserTest.JAVAJIGI).isDeleted()).isTrue();
    }

    @Test
    @DisplayName("[실패] 자신이 생성하지 않은 질문을 삭제하려는 경우 CannotDeleteException 예외가 발생한다.")
    void 답변_삭제_불가능() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.delete(NsUserTest.SANJIGI));
    }

}
