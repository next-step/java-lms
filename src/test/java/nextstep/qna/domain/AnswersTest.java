package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {
    public static Answer A1;
    public static Answer A2;
    public static Answers ans1;

    @BeforeEach
    public void setUp() {
        A1 = AnswerTest.aAnswer();
        A2 = AnswerTest.otherAnswer();
        ans1 = new Answers(List.of(A1, A2));
    }

    @Test
    public void add() {
        Answers answers = new Answers();
        answers.add(A1);
        answers.add(A2);

        assertThat(answers).isEqualTo(ans1);
    }

    @Test
    public void delete() throws Exception {
        Answers answers = new Answers();
        A2.delete(NsUserTest.SANJIGI);
        answers.add(A2);

        assertThat(answers.history()).isEqualTo(List.of(DeleteHistoryTest.A2DeleteHistory));
    }
}
