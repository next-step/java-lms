package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void 답변을_등록한다() {
        Answers answers = new Answers();
        answers.add(A1);
        answers.add(A2);

        assertThat(answers.getAnswerList()).contains(A1, A2);
    }

    @Test
    public void 등록된_모든_답변을_삭제하며_이력을_반환한다() throws CannotDeleteException {
        Answers answers = new Answers();
        answers.add(A1);
        answers.add(A2);

        assertThat(answers.deleteAllBy(NsUserTest.JAVAJIGI))
                .hasSize(2)
                .allSatisfy(deleteHistory -> assertThat(deleteHistory).isInstanceOf(DeleteHistory.class));
    }

}
