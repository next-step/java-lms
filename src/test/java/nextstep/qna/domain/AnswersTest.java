package nextstep.qna.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    NsUser writer = new NsUser(2L, "user1", "1234", "jhm9595","jhm@gmail.com");

    Question question = new Question(writer, "Question title", "Question Content");

    Answers answers = question.getAnswers();

    Answer answer = new Answer(writer, question, "Answer contents");

    NsUser otherWriter = new NsUser(3L, "user2", "1234", "jhm9595","jhm@gmail.com");
    Answer otherAnswer = new Answer(otherWriter, question, "Answer contents");

    @Test
    void add() {
        answers.add(answer);
        assertThat(answers.getAnswers()).hasSize(1);
    }

    @Test
    void isEmpty() {
        assertThat(answers.isEmpty()).isTrue();
    }

    @Test
    void hasOthersAnswers() {
        answers.add(otherAnswer);
        assertThat(answers.hasOthersAnswers(writer)).isTrue();
    }

    @Test
    void hasNotOthersAnswers() {
        answers.add(answer);
        assertThat(answers.hasOthersAnswers(writer)).isFalse();
    }

    @Test
    void delete() {
        answers.add(answer);
        assertThat(answers.delete()).hasSize(1);
    }
}