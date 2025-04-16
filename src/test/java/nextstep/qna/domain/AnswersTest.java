package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    @Test
    @DisplayName("답변을 중복 등록하면, 에러가 발생한다.")
    void addDuplicateAnswer() {
        Question question = QuestionTest.createQuestion(NsUserTest.JAVAJIGI);
        Answer answer = AnswerTest.createAnswer(NsUserTest.JAVAJIGI, question);

        Answers answers = new Answers();
        answers.add(answer);
        assertThatThrownBy(() -> answers.add(answer)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("답변을 등록하면, 답변이 등록된다.")
    void addAnswer() {
        Question question = QuestionTest.createQuestion(NsUserTest.JAVAJIGI);
        Answer answer = AnswerTest.createAnswer(NsUserTest.JAVAJIGI, question);

        Answers answers = new Answers();
        answers.add(answer);
        assertThat(answers.getAnswers()).contains(answer);
    }

    @Test
    @DisplayName("답변을 모두 획득한다.")
    void getAnswers() {
        Question question = QuestionTest.createQuestion(NsUserTest.JAVAJIGI);
        Answer answer = AnswerTest.createAnswer(NsUserTest.JAVAJIGI, question);
        Answers answers = new Answers();
        answers.add(answer);

        assertThat(answers.getAnswers()).hasSize(1)
                .containsAll(List.of(answer));
    }

    @Test
    @DisplayName("질문을 삭제하면, 질문과 답변 삭제 히스토리를 반환한다.")
    void deleteByAndReturnHistory() {
        Question question = QuestionTest.createQuestion(NsUserTest.JAVAJIGI);
        Answer answer = AnswerTest.createAnswer(NsUserTest.JAVAJIGI, question);
        Answers answers = new Answers();
        answers.add(answer);

        assertThat(answers.deleteAll()).hasSize(1).containsAll(
                List.of(new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, null))
        );
    }

    @Test
    @DisplayName("답변을 작성한 사용자가 맞는지 확인한다.")
    void isOwner() {
        Question question = QuestionTest.createQuestion(NsUserTest.JAVAJIGI);
        Answer answer = AnswerTest.createAnswer(NsUserTest.JAVAJIGI, question);
        Answers answers = new Answers();
        answers.add(answer);

        assertThat(answers.isOwner(NsUserTest.JAVAJIGI)).isTrue();
        assertThat(answers.isOwner(NsUserTest.SANJIGI)).isFalse();
    }

}
