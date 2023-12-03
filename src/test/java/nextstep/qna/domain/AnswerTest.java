package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("작성자와 로그인 유저가 같음")
    @Test
    public void writerAndLoginEquals() throws CannotDeleteException {
        A1.delete(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @DisplayName("작성자와 로그인 유저가 다름")
    @Test
    public void writerAndLoginNotEquals() {
        assertThatThrownBy(() -> {
            A1.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
