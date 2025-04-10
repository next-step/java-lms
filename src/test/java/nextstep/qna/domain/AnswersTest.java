package nextstep.qna.domain;

import static nextstep.qna.domain.AnswerTest.A1;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

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
    void deleteAll() throws CannotDeleteException {
        // given
        Answers answers = new Answers();
        Answer answer1 = AnswerTest.A1;
        Answer answer2 = AnswerTest.A1;
        answers.add(answer1);
        answers.add(answer2);

        // when
        answers.deleteAll(AnswerTest.A1.getWriter());

        // then
        assertTrue(answer1.isDeleted());
        assertTrue(answer2.isDeleted());
    }

}