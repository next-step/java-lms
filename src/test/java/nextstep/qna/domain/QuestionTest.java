package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("질문 테스트")
public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("답변이 없는 경우 질문을 삭제할 수 있다")
    @Test
    void deleteQuestion() throws CannotDeleteException {
        Q1.deleteQuestion(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("다른 사람의 질문을 삭제 할 수 없다")
    @Test
     void authorizationDeleteQuestion() {
        assertThatThrownBy(() -> {
            Q1.deleteQuestion(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
