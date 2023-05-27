package nextstep.qna.domain;

import static nextstep.qna.exception.QnAExceptionCode.NOT_EXIST_AUTHENTICATION;

import java.util.List;
import nextstep.qna.CannotDeleteException;
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
    void Answers_답변_삭제() throws CannotDeleteException {
        answers.delete(NsUserTest.JAVAJIGI);

        Assertions.assertThat(answer1.isDeleted()).isTrue();
        Assertions.assertThat(answer2.isDeleted()).isTrue();

    }

    @Test
    void Answers_다른_사용자_답변이_있는_경우() throws CannotDeleteException {
        Answer answer3 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents3");

        answers.add(answer3);

        Assertions.assertThatThrownBy(() -> answers.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(NOT_EXIST_AUTHENTICATION.message());
    }
}
