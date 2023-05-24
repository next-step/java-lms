package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class AnswersTest {

    @Test
    void deleteAnswers() {
        Answers answers = new Answers();
        answers.addAnswer(QuestionTest.Q1, AnswerTest.A1);
        answers.addAnswer(QuestionTest.Q1, AnswerTest.A3);

        DeleteHistories deleteHistories = answers.deleteAnswers(NsUserTest.JAVAJIGI);
        assertThat(AnswerTest.A1.isDeleted()).isTrue();
        assertThat(AnswerTest.A3.isDeleted()).isTrue();
        assertThat(deleteHistories.getDeleteHistories().size()).isEqualTo(2);
    }
}