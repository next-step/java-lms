package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @Test
    @DisplayName("답변 리스트를 생성할 수 있다")
    public void answers() {
        Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        assertThat(Answers.init().added(A1)).isEqualTo(Answers.init().added(A1));
    }

    @Test
    @DisplayName("답변 목록 중 다른 사람이 쓴 답변이 있는지 확인할 수 있다")
    public void answers_of_other() {
        Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
        Answers answers = Answers.init().added(A1).added(A2);

        assertThat(answers.hasAnswerExcept(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("답변 목록 삭제 시 히스토리를 생성할 수 있다")
    public void delete_answers_history() {
        Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
        Answers answers = Answers.init().added(A1).added(A2);

        assertThat(answers.deleted()).hasOnlyElementsOfType(DeleteHistory.class).hasSize(2);
    }


}
