package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    @DisplayName("답변 추가가 잘 되는지")
    @Test
    void addTest() {
        Answers answers = new Answers();
        Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "답변 내용");

        answers.add(answer);

        assertThat(answers.size()).isEqualTo(1);
    }
}
