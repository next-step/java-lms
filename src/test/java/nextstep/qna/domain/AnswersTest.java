package nextstep.qna.domain;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;

public class AnswersTest {
    @Test
    void shouldContainsAnswer_whenAddAnswer() {
        Answers answers = new Answers();
        answers.add(A1);

        assertThat(answers.contains(A1)).isTrue();
    }

    @Test
    void shouldEnsureAllAnswersOwnedByUser() {
        Answers answers = new Answers();
        answers.add(A1);
        answers.add(A2);

        assertThatThrownBy(() -> {
            answers.ensureAllAnswersOwnedByUser(SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
