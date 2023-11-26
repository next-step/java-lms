package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문을 삭제할 때, 로그인한 사용자와 다르면 삭제 할 수 없다.")
    @Test
    void deleteTest() {
        final NsUser qwe5507 = new NsUser(1L, "qwe5507", "1234", "simpson", "qwe5507@gmail.com");
        final NsUser pobi = new NsUser(2L, "zxc5507", "1234", "pobi", "pobi@gmail.com");
        final Question question = new Question(qwe5507, "title", "contents");

        assertThatThrownBy(() -> {
            question.delete(pobi);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
