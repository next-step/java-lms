package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("답변 관련 기능")
public class AnswerTest {
    private Answer A1;
    private Answer A2;
    private Question Q1;

    @BeforeEach
    void setUp() {
        this.Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        this.A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        this.A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");
    }

    @DisplayName("답변 삭제시 로그인 사용자와 질문자가 같을 경우 예외를 발생하지 않는다.")
    @Test
    void when_LoginUserIsEqualToQuestionWriter_Expects_DoesNotThrowException() {
        Q1.addAnswer(A1);
        Q1.addAnswer(A1);
        Q1.addAnswer(A1);

        List<Answer> answers = Q1.getAnswers();

        assertThat(answers).allSatisfy(answer ->
                assertThatNoException().isThrownBy(answer::delete));
    }

    @DisplayName("답변 삭제시 로그인 사용자와 질문자가 같지 않을 경우 예외를 발생한다.")
    @Test
    void when_LoginUserIsNotEqualToQuestionWriter_Expects_ThrowException() {
        Q1.addAnswer(A1);
        Q1.addAnswer(A2);
        Q1.addAnswer(A1);

        List<Answer> answers = Q1.getAnswers();


        assertThat(answers).anySatisfy(answer ->
                assertThatThrownBy(answer::delete)
                        .isInstanceOf(CannotDeleteException.class));
    }
}
