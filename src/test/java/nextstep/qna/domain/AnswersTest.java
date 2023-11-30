package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @Test
    public void delete_성공() throws Exception {
        Answer A1 = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer A2 = new Answer(12L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
        Answers answers = new Answers(Arrays.asList(A1, A2));

        answers.delete(NsUserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isTrue();
    }
}
