package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변을 삭제하면 해당 답변에 대한 History 객체를 반환한다.")
    @Test
    void answerDeleteTest() throws CannotDeleteException {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        assertThat(answer.delete(NsUserTest.JAVAJIGI)).isInstanceOf(DeleteHistory.class);
        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("답변 작성자와 로그인 유저가 같지 않으면 CannotDeleteException을 던진다.")
    @Test
    void answerDeleteExceptionTest() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        assertThatThrownBy(() -> answer.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
