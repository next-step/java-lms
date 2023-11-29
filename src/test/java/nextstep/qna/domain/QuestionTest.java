package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문을 삭제하는 경우 삭제 상태가 변경된다.")
    void deleteQuestionTest() {
        assertThat(Q1.isDeleted()).isFalse();
        Q1.setDeleted();
        assertThat(Q1.isDeleted()).isTrue();
    }
}
