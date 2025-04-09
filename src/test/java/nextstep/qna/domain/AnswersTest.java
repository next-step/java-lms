
package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q2, "Answers Contents3");
    public static final Answer A4 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q2, "Answers Contents4");


    @Test
    public void 삭제할때_답변들의_모든작성자가_동일하면_삭제가능() throws CannotDeleteException {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(A1);
        answerList.add(A2);
        Answers answers = new Answers(answerList);

        assertThat(answers.validateDeletableBy(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    public void 삭제할때_답변들의_모든작성자가_동일하지않으면_CannotDeleteException_발생() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(A3);
        answerList.add(A4);
        Answers answers = new Answers(answerList);

        assertThatThrownBy(() -> answers.validateDeletableBy(NsUserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}