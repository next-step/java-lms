package nextstep.qna.domain;

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
    public void delete() {
        assertThat(ans1.delete()).isEqualTo(List.of(DeleteHistoryTest.a1DeleteHistory, DeleteHistoryTest.a2DeleteHistory));
    }
}
