package nextstep.qna.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @Test
    void create() {
        Answers answers = new Answers(List.of(A1, A2));
        assertThat(answers).isEqualTo(new Answers(List.of(A1, A2)));
    }

    @Test
    void add() {
        Answers answers = new Answers();
        answers.add(A1);

        assertThat(answers).isEqualTo(new Answers(List.of(A1)));
    }

    @Test
    void get() {
        Answers answers = new Answers(List.of(A1, A2));
        assertThat(answers.get()).isEqualTo(List.of(A1, A2));
    }

    @Test
    void toDeleteHistory() {
        Answers answers = new Answers(List.of(A1, A2));
        List<DeleteHistory> deleteHistory = answers.toDeleteHistory();
        assertThat(deleteHistory).isEqualTo(List.of(A1.toDeleteHistory(), A2.toDeleteHistory()));
    }
}
