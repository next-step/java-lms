package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AnswersTest {
    public static final Answers ans1 = new Answers(List.of(AnswerTest.A1, AnswerTest.A2));

    @Test
    public void add() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        assertThat(answers).isEqualTo(ans1);
    }

    @Test
    public void delete() throws Exception {
        Answers answers = new Answers();
        answers.add(AnswerTest.A2);

        assertThat(answers.delete(NsUserTest.SANJIGI)).isEqualTo(List.of(DeleteHistoryTest.A2DeleteHistory));
    }
}
