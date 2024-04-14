package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {

    @Test
    @DisplayName("질문 작성자 외에 다른 사람이 작성한 답변이 있을 경우 예외를 던진다.")
    void validateHasOtherUserAnswers_throw() {

        Answers answers = new Answers();
        answers.addAnswer(AnswerTest.A1);
        answers.addAnswer(AnswerTest.A2);

        Assertions.assertThatThrownBy(() ->
                answers.validateHasOtherUserAnswers(NsUserTest.JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문 작성자 외에 다른 사람이 작성한 답변이 없을 경우 예외를 던지지 않는다.")
    void validateHasOtherUserAnswers() {

        Answers answers = new Answers();
        answers.addAnswer(AnswerTest.A1);

        assertDoesNotThrow(() ->
                answers.validateHasOtherUserAnswers(NsUserTest.JAVAJIGI)
        );
    }

    @Test
    @DisplayName("질문 작성자의 답변만 있다면 삭제한다.")
    void delete_success() throws CannotDeleteException {

        Answers answers = new Answers();
        answers.addAnswer(AnswerTest.A1);
        answers.delete(NsUserTest.JAVAJIGI);

        List<Boolean> deletedStatuses =  answers.getAnswers().stream()
                .map(Answer::isDeleted)
                .collect(Collectors.toList());

        Assertions.assertThat(deletedStatuses).containsExactly(true);
    }

    @Test
    @DisplayName("질문 작성자 외의 답변이 있다면 예외를 던진다.")
    void delete_fail() {

        Answers answers = new Answers();
        answers.addAnswer(AnswerTest.A1);
        answers.addAnswer(AnswerTest.A2);


        Assertions.assertThatThrownBy(() ->
                answers.delete(NsUserTest.JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}