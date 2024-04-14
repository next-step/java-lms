package nextstep.qna.domain;


import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("작성자가 다르면 삭제할 수 없으며, 예외 발생")
    void Question_삭제_예외() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문 정상 삭제시 예외 발생하지 않으며, isDeleted값은 true")
    void Question_삭제() {
        assertThatCode(() -> Q1.delete(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThat(Q1.isDeleted()).isEqualTo(true);
    }
}
