package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answer testAnswer;

    @BeforeEach
    public void initAnswer() {
        testAnswer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "answer test");
    }

    @Test
    @DisplayName("답변 삭제 테스트")
    void testDeleteAnswer() throws CannotDeleteException {
        DeleteHistory deleteHistory = testAnswer.delete(NsUserTest.JAVAJIGI);
        assertThat(testAnswer.isDeleted()).isTrue();
        assertThat(deleteHistory.toString())
                .contains(ContentType.ANSWER.toString())
                .contains("javajigi");
    }

    @Test
    @DisplayName("이미 삭제된 답변 삭제할 경우 에러 발생")
    void testDeleteAnswerAlreadyDeleted() throws CannotDeleteException {
        testAnswer.delete(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> testAnswer.delete(NsUserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("작성자와 로그인 유저가 다른 경우 에러 발생")
    void testDeleteAnswerNotAuthored() throws CannotDeleteException {
        testAnswer.delete(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> testAnswer.delete(NsUserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
