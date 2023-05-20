package nextstep.qna.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class AnswersTest {
    @Test
    void add_answer() {
        // given
        Answers answers = new Answers();

        // when
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        // then
        assertThat(answers).isEqualTo(new Answers(AnswerTest.A1, AnswerTest.A2));
    }

    @Test
    void delete() {
        // given
        Answers answers = new Answers(AnswerTest.A1, AnswerTest.A2);

        // when
        answers.delete();

        // then
        Optional<Answer> notDeletedAnswer = answers.answers().stream()
                .filter(answer -> !answer.isDeleted())
                .findAny();
        assertThat(notDeletedAnswer).isEmpty();
    }
}
