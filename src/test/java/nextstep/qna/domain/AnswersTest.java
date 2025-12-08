package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnswersTest {
    @Test
    void create() {
        Answers answers = new Answers(new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));

        Assertions.assertThat(answers.getAnswers()).hasSize(1);
    }
}
