package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문을 삭제할 때, 로그인한 사용자와 다르면 삭제 할 수 없다.")
    @Test
    void 질문_삭제시_로그인한_사용자와_다를떄() {
        assertThatThrownBy(() -> {
            Q1.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문을 삭제할 때, 삭제상태를 true로 변경한다.")
    @Test
    void 질문_삭제시_삭제상태를_변경() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);
        Q1.addAnswer(AnswerTest.A1);
        assertThat(Q1.isDeleted()).isTrue();
    }

}
