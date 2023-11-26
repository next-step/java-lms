package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문을 삭제한다. 소유자가 다르면 질문을 삭제 할 수 없다. 질문과 답변이 삭제되고 이력을 반환한다")
    void validateDeleteOwner() {
        assertThatThrownBy(() -> Q1.deleteAll(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
