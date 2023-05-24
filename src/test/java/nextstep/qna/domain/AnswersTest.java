package nextstep.qna.domain;

import nextstep.qna.exception.QnAException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AnswersTest {

    private static Answers answers;
    private static Answer answer1;
    private static Answer answer2;

    @BeforeAll
    static void init() {
        answers = new Answers();
        answer1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answer2 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");

        answers.add(answer1);
        answers.add(answer2);
    }

    @Test
    void Answers_답변_추가() {
        Assertions.assertThat(answers.getAnswers()).contains(answer1);
        Assertions.assertThat(answers.getAnswers()).contains(answer2);
    }

    @Test
    void Answers_답변_삭제() throws QnAException {
        answers.delete(NsUserTest.JAVAJIGI);

        Assertions.assertThat(answer1.isDeleted()).isTrue();
        Assertions.assertThat(answer2.isDeleted()).isTrue();

    }
}
