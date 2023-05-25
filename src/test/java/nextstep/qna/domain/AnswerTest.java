package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("답변 기능 관련 테스트")
public class AnswerTest {
    public Answer A1;
    public Answer A2;
    public Question Q1;
    private DeleteHistories deleteHistories;


    @BeforeEach
    void setUp() {
        this.deleteHistories = new DeleteHistories();
        Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");
    }

    @Test
    @DisplayName("답변을 삭제시에 모든 답변이 로그인 사용자가 작성한 것이라면 예외가 던지지 않는다.")
    void delete_AllAnswerFromLoginUser_NotThrowException() {
        Q1.addAnswer(A1);
        Q1.addAnswer(A1);
        Q1.addAnswer(A1);

        List<Answer> answers = Q1.getAnswers();

        assertThat(answers).allSatisfy(answer ->
                assertThatNoException().isThrownBy(() ->
                        answer.delete(deleteHistories)));
    }

    @Test
    @DisplayName("답변을 삭제시에 하나라도 로그인 사용자가 작성한 것이 아니라면 예외를 던진다.")
    void delete_IfOneAnswerIsNotFromLoginUser_ThrowException() {
        Q1.addAnswer(A1);
        Q1.addAnswer(A1);
        Q1.addAnswer(A2);

        List<Answer> answers = Q1.getAnswers();

        assertThat(answers).anySatisfy(answer ->
                assertThatThrownBy(() -> answer.delete(deleteHistories))
                        .isInstanceOf(CannotDeleteException.class));
    }

    @Test
    @DisplayName("답변 삭제시 isDelete값을 true로 변경한다.")
    void delete_DeleteAnswer_IsDeleteIsTrue() {
        A1.delete(deleteHistories);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제처리하지 않은 답변은 isDelete값이 false다.")
    void delete_DontDeleteAnswer_IsDeleteIsFalse() {
        assertThat(A1.isDeleted()).isFalse();
    }
}
