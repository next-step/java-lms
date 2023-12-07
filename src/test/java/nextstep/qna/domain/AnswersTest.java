package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AnswersTest {

    @DisplayName("전달받은 answer 객체를 answers 객체에 추가한다.")
    @Test
    void addAnswerTest() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);

        assertThat(answers.getValues().size()).isEqualTo(1);
    }

    @DisplayName("답변들을 삭제하면 해당 답변들에 대한 History 리스트 객체를 반환한다.")
    @Test
    void deleteTest() throws CannotDeleteException {
        Answers answers = new Answers(List.of(
                new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"),
                new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2")));

        assertThat(answers.delete(NsUserTest.JAVAJIGI).size()).isEqualTo(answers.getValues().size());
    }
}
