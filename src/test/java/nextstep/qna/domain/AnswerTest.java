package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {

    @DisplayName("작성자와 로그인 유저가 같음")
    @Test
    public void writerAndLoginEquals() throws CannotDeleteException {
        Question Q1 = new Question("title1", new Contents(NsUserTest.JAVAJIGI.getUserId(), "contents1"));
        Question Q2 = new Question("title2", new Contents(NsUserTest.SANJIGI.getUserId(), "contents2"));

        Answer A1 = new Answer(Q1, new Contents(NsUserTest.JAVAJIGI.getUserId(), "Answers Contents1"));
        Answer A2 = new Answer(Q2, new Contents(NsUserTest.SANJIGI.getUserId(), "Answers Contents2"));

        A1.delete(NsUserTest.JAVAJIGI.getUserId());
        assertThat(A1.isDeleted()).isTrue();

        A2.delete(NsUserTest.SANJIGI.getUserId());
        assertThat(A2.isDeleted()).isTrue();
    }

    @DisplayName("작성자와 로그인 유저가 다름")
    @Test
    public void writerAndLoginNotEquals() {
        Question Q1 = new Question("title1", new Contents(NsUserTest.JAVAJIGI.getUserId(), "contents1"));
        Question Q2 = new Question("title2", new Contents(NsUserTest.SANJIGI.getUserId(), "contents2"));

        Answer A1 = new Answer(Q1, new Contents(NsUserTest.JAVAJIGI.getUserId(), "Answers Contents1"));
        Answer A2 = new Answer(Q2, new Contents(NsUserTest.SANJIGI.getUserId(), "Answers Contents2"));

        assertThatThrownBy(() -> {
            A1.delete(NsUserTest.SANJIGI.getUserId());
        }).isInstanceOf(CannotDeleteException.class);

        assertThatThrownBy(() -> {
            A2.delete(NsUserTest.JAVAJIGI.getUserId());
        }).isInstanceOf(CannotDeleteException.class);
    }
}
