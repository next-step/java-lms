package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.answer.Answer;
import nextstep.qna.domain.answer.Answers;
import nextstep.users.domain.NsUserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static nextstep.qna.domain.fixture.AnswerFixture.A1;
import static nextstep.qna.domain.fixture.AnswerFixture.A2;
import static nextstep.qna.domain.fixture.QuestionFixture.Q1;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class AnswersTest {

    private Answers answers = new Answers();

    @Test
    void answersAddTest() {
        // Given
        Answer answer = new Answer(null, NsUserFixture.JAVAJIGI, Q1, "Answers Contents1");
        List<Answer> initialAnswers = Arrays.asList(A1, A2);

        // When
        answers.getAnswers().addAll(initialAnswers);
        answers.add(answer);

        // Then
        assertThat(answers.getAnswers())
                .hasSize(3);
    }

    @Test
    @DisplayName("다른사람이 답변을 단 글을 삭제할 경우 예외가 발생한다.")
    void testDeleteReplyByDifferentUser() {
        // Given
        answers.add(A1);
        answers.add(A2);

        // When & Then
        assertThatThrownBy(() -> answers.delete(NsUserFixture.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("답변을 삭제할 경우 답변이 삭제된다.")
    void testDeleteReplyBySameUser() {
        // Given
        answers.add(A1);

        // When
        answers.delete(NsUserFixture.JAVAJIGI);

        // Then
        assertThat(answers.getAnswers())
                .hasSize(1);
    }

}