package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void 다른사용자답변_체크테스트() {
        Answers answers = Answers.init().add(A1).add(A2);
        assertThat(answers.checkAnswer(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    public void 답변삭제시_히스토리생성테스트() {
        Answers answers = Answers.init().add(A1);
        assertThat(answers.deleted(NsUserTest.JAVAJIGI)).hasOnlyElementsOfType(DeleteHistory.class).hasSize(1);
    }
}
