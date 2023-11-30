package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("[Answer.deleteIfWriter()] 주어진 사용자가 답변자라면 -> 삭제")
    public void deleteTest() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "hello");
        answer.deleteIfWriter(NsUserTest.JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("[Answer.deleteIfWriter()] 주어진 사용자와 답변자가 다르다면 -> 삭제 거부")
    public void deleteByWrongWriterTest() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "hello");
        answer.deleteIfWriter(NsUserTest.SANJIGI);

        assertThat(answer.isDeleted()).isFalse();
    }
}
