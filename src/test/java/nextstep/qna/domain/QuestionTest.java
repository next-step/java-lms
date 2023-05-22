package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("질문 테스트")
public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("답변이 없는 경우 질문을 삭제할 수 있다")
    @Test
    void deleteQuestion() {
        Q1.deleteQuestion();
        Assertions.assertThat(Q1.isDeleted()).isTrue();
    }
    
}
