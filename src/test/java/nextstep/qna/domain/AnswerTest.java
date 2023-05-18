package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.users.domain.NsUserTest;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("질문 작성자와 답변 작성자가 다름")
    @Test
    void test1() {
        assertThat(A1.isOwner(NsUserTest.SANJIGI)).isFalse();
        assertThat(A2.isOwner(NsUserTest.JAVAJIGI)).isFalse();
    }

    @DisplayName("질문 작성자와 답변 작성자가 같음")
    @Test
    void test2() {
        assertThat(A1.isOwner(NsUserTest.JAVAJIGI)).isTrue();
        assertThat(A2.isOwner(NsUserTest.SANJIGI)).isTrue();
    }
}
