package nextstep.qna.domain;

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

    @DisplayName("전달받은 유저가 답변들을 작성한 유저와 동일하면 true를 반환한다.")
    @Test
    void isAnswerOwnerTest_true() {
        Answers answers = new Answers(List.of(AnswerTest.A1, AnswerTest.A1));

        assertThat(answers.isAnswerOwner(NsUserTest.JAVAJIGI)).isTrue();
    }

    @DisplayName("전달받은 유저가 답변들을 작성한 유저와 동일하지 않으면 false를 반환한다.")
    @Test
    void isAnswerOwnerTest_false() {
        Answers answers = new Answers(List.of(AnswerTest.A1, AnswerTest.A2));

        assertThat(answers.isAnswerOwner(NsUserTest.JAVAJIGI)).isFalse();
    }

    @DisplayName("답변들을 삭제하면 해당 답변들에 대한 History 리스트 객체를 반환한다.")
    @Test
    void deleteTest() {
        Answers answers = new Answers(List.of(
                new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"),
                new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2")));

        assertThat(answers.delete().size()).isEqualTo(answers.getValues().size());
    }
}
