package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 작성자_본인인지_확인(){
        assertThat(A1.isOwner(NsUserTest.JAVAJIGI)).isTrue();
        assertThat(A1.isOwner(NsUserTest.SANJIGI)).isFalse();
    }

}
