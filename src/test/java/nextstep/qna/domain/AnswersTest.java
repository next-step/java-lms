package nextstep.qna.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswersTest {

    @Test
    @DisplayName("Answers에 새로운 Answer를 추가한다.")
    void add() {
        // given
        Answers answers = new Answers();
        Answer answer = new Answer();

        // when
        answers.add(answer);

        // then
        Assertions.assertThat(answers.getAnswers().contains(answer)).isTrue();
    }

    @Test
    @DisplayName("Answers에 있는 모든 Answer를 삭제한다.")
    void deleteAll() {
        // given
        Answers answers = new Answers();
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        answers.add(answer1);
        answers.add(answer2);

        // when
        answers.deleteAll();

        // then
        assertTrue(answer1.isDeleted());
        assertTrue(answer2.isDeleted());
    }

}