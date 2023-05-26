package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {
    @Test
    public void add() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);
        assertThat(answers.getAnswers()).hasSize(2);
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        assertThatThrownBy(() -> answers.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}