package nextstep.qna.domain;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A3;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;

import nextstep.qna.CannotDeleteException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswersTest {

    @DisplayName("답변들을 삭제 시 삭제 이력을 반환한다")
    @Test
    void shouldReturnDeleteHistory_whenDeletePossible() throws CannotDeleteException {
        Answers answers = new Answers(A1, A3);
        assertThat(answers.deleteAll(JAVAJIGI)).hasSize(2);
    }

    @DisplayName("Answers에 Answer를 추가할 수 있다")
    @Test
    void shouldAddAnswerToAnswers() {
        Answers answers = new Answers(A1);
        answers.add(A3);
        assertThat(answers.answers()).hasSize(2);
    }

}