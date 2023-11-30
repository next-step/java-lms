package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("[Question.deleteIfWriter()] 삭제를 요청하면 -> 자신을 삭제 상태로 만든다.")
    public void deleteTest() {
        Question question = new Question(NsUserTest.JAVAJIGI, "hello", "world!");
        question.deleteIfWriter(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();

    }
}
